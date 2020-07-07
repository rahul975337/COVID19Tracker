package com.rt.apps.covid_19.tracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.google.gson.Gson

import com.rt.apps.covid_19.Essentials.EssentialActivity
import com.rt.apps.covid_19.Infected.InfectedActivity
import com.rt.apps.covid_19.Prevention.PreventionActivity
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.Symptoms.SymptomActivity
import com.rt.apps.covid_19.About.AboutActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false

    lateinit var stateListAdapter: StateListAdapter

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/////////setting animations/////
        val fabOpen = AnimationUtils.loadAnimation(
            this,
            R.anim.fab_open
        )
        val fabClose = AnimationUtils.loadAnimation(
            this,
            R.anim.fab_close
        )
        val fabRClockwise = AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_clockwise
        )
        val fabRAntiClockwise = AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_anticlockwise
        )
        //////setting up fab////
        Tracker_fab.setOnClickListener {
            if (isOpen) {
                About_fab.startAnimation(fabClose)
                Symptoms_fab.startAnimation(fabClose)
                Essentials_fab.startAnimation(fabClose)
                Prevention_fab.startAnimation(fabClose)
                Infected_fab.startAnimation(fabClose)

                    //visibility//
                    About_fab.visibility = View.INVISIBLE
                    Symptoms_fab.visibility = View.INVISIBLE
                    Essentials_fab.visibility = View.INVISIBLE
                    Prevention_fab.visibility = View.INVISIBLE
                    Infected_fab.visibility = View.INVISIBLE
                //clickable

                        About_fab.isClickable = false
                        Symptoms_fab.isClickable = false
                        Essentials_fab.isClickable = false
                        Prevention_fab.isClickable = false
                        Infected_fab.isClickable = false
                //disabling when fab close

                            About_fab.isEnabled = false
                            Symptoms_fab.isEnabled = false
                            Essentials_fab.isEnabled = false
                            Prevention_fab.isEnabled = false
                            Infected_fab.isEnabled = false

                Tracker_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {


                    About_fab.startAnimation(fabOpen)
                    Symptoms_fab.startAnimation(fabOpen)
                    Essentials_fab.startAnimation(fabOpen)
                    Prevention_fab.startAnimation(fabOpen)
                    Infected_fab.startAnimation(fabOpen)
                     Tracker_fab.startAnimation(fabRAntiClockwise)
                        ////

                    About_fab.visibility = View.VISIBLE
                    Symptoms_fab.visibility = View.VISIBLE
                    Essentials_fab.visibility = View.VISIBLE
                    Prevention_fab.visibility = View.VISIBLE
                    Infected_fab.visibility = View.VISIBLE
                //

                        About_fab.isClickable = true
                        Symptoms_fab.isClickable = true
                        Essentials_fab.isClickable = true
                        Prevention_fab.isClickable = true
                        Infected_fab.isClickable = true
                ///////////

                           About_fab.isEnabled = true
                            Symptoms_fab.isEnabled = true
                            Essentials_fab.isEnabled = true
                            Prevention_fab.isEnabled = true
                            Infected_fab.isEnabled = true
                isOpen = true
            }
            About_fab.setOnClickListener {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            Symptoms_fab.setOnClickListener {
                val intent = Intent(this, SymptomActivity::class.java)
                startActivity(intent)
            }
                Essentials_fab.setOnClickListener {
                    val intent = Intent(this, EssentialActivity::class.java)
                    startActivity(intent)
                }
               Prevention_fab.setOnClickListener {
                    val intent = Intent(this, PreventionActivity::class.java)
                    startActivity(intent)
                }
                Infected_fab.setOnClickListener {
                    val intent = Intent(this, InfectedActivity::class.java)
                    startActivity(intent)
                }
        }
/////////////////////

        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_header, list, false))

        fetchResults()
        swipeToRefresh.setOnRefreshListener {
            fetchResults()
        }
        initWorker()
        list.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (list.getChildAt(0) != null) {
                    swipeToRefresh.isEnabled = list.firstVisiblePosition === 0 && list.getChildAt(
                        0
                    ).top === 0
                }
            }
        })
    }

    private fun fetchResults() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.clone().execute() }
            if (response.isSuccessful) {
                swipeToRefresh.isRefreshing = false
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombinedData(data.statewise[0])
                    bindStateWiseData(data.statewise.subList(0, data.statewise.size))
                }
            }
        }
    }

    private fun bindStateWiseData(subList: List<StatewiseItem>) {
        stateListAdapter = StateListAdapter(subList)
        list.adapter = stateListAdapter
    }

    private fun bindCombinedData(data: StatewiseItem) {
        val lastUpdatedTime = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdatedTv.text = "Last Updated\n ${getTimeAgo(
            simpleDateFormat.parse(lastUpdatedTime)
        )}"

        confirmedTv.text = data.confirmed
        activeTv.text = data.active
        recoveredTv.text = data.recovered
        deceasedTv.text = data.deaths

    }

    @InternalCoroutinesApi
    private fun initWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val notificationWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "JOB_TAG",
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }


}

fun getTimeAgo(past: Date): String {
    val now = Date()
    val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
    val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

    return when {
        seconds < 60 -> {
            "Few seconds ago"
        }
        minutes < 60 -> {
            "$minutes minutes ago"
        }
        hours < 24 -> {
            "$hours hour ${minutes % 60} min ago"
        }
        else -> {
            SimpleDateFormat("dd/MM/yy, hh:mm a").format(past).toString()
        }
    }
}
