package com.rt.apps.covid_19

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.google.gson.Gson
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
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)
        //////setting up fab////
        extended_fab.setOnClickListener {
            if (isOpen) {
                extended_fab1.startAnimation(fabClose)
                extended_fab2.startAnimation(fabClose)
                //visibility//
                extended_fab1.visibility = View.INVISIBLE
                extended_fab2.visibility = View.INVISIBLE
                extended_fab1.isClickable = false
                extended_fab2.isClickable = false
                extended_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {
                extended_fab1.startAnimation(fabOpen)
                extended_fab2.startAnimation(fabOpen)
                extended_fab.startAnimation(fabRAntiClockwise)
                extended_fab1.visibility = View.VISIBLE
                extended_fab2.visibility = View.VISIBLE
                extended_fab1.isClickable = true
                extended_fab2.isClickable = true
                isOpen = true
            }
            extended_fab1.setOnClickListener {
                val intent = Intent(this, PrecautionActivity::class.java)
                startActivity(intent)
            }
            extended_fab2.setOnClickListener {
                val intent = Intent(this, precautionactivity2::class.java)
                startActivity(intent)
            }
        }
//       ////////
//
//        val fab: View = findViewById(R.id.extended_fab)
//        extended_fab.setOnClickListener {
//            val intent=Intent(this,PrecautionActivity::class.java)
//            startActivity(intent)
//        }
//        //////////////////////
//        ////////
//        val fab1: View = findViewById(R.id.extended_fab1)
//        extended_fab1.setOnClickListener {
//            val intent=Intent(this,precautionactivity1::class.java)
//            startActivity(intent)
//        }
//        //////////////////////
//        ////////
//        val fab2: View = findViewById(R.id.extended_fab2)
//        extended_fab2.setOnClickListener {
//            val intent=Intent(this,precautionactivity2::class.java)
//            startActivity(intent)
//        }
//        //////////////////////

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
