package com.rt.apps.covid_19.Prevention

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.rt.apps.covid_19.About.AboutActivity
import com.rt.apps.covid_19.Essentials.EssentialActivity
import com.rt.apps.covid_19.Infected.InfectedActivity
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.Symptoms.SymptomActivity
import com.rt.apps.covid_19.tracker.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class PreventionActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false
    private lateinit var homeBtn: ImageButton
    private lateinit var addBtn: ImageButton
    private lateinit var notiBtn: ImageButton
    private lateinit var searchBtn: ImageButton
    private lateinit var profileBtn: ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPreventionPagerViewAdapter: PreventionPagerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prevention)
/////////////////////////////////////
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
        Prevention_fab.setOnClickListener {
            if (isOpen) {
                Tracker_fab.startAnimation(fabClose)
                Symptoms_fab.startAnimation(fabClose)
                About_fab.startAnimation(fabClose)
                Infected_fab.startAnimation(fabClose)
                Essentials_fab.startAnimation(fabClose)

                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                Symptoms_fab.visibility = View.INVISIBLE
                About_fab.visibility = View.INVISIBLE
                Infected_fab.visibility = View.INVISIBLE
                Essentials_fab.visibility = View.INVISIBLE
                //clickable

                Tracker_fab.isClickable = false
                Symptoms_fab.isClickable = false
                About_fab.isClickable = false
                Infected_fab.isClickable = false
                Essentials_fab.isClickable = false
                //disabling when fab close

                Tracker_fab.isEnabled = false
                Symptoms_fab.isEnabled = false
                About_fab.isEnabled = false
                Infected_fab.isEnabled = false
                Essentials_fab.isEnabled = false
////////////////////////
                Prevention_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                Symptoms_fab.startAnimation(fabOpen)
                About_fab.startAnimation(fabOpen)
                Infected_fab.startAnimation(fabOpen)
                Essentials_fab.startAnimation(fabOpen)
                ////
                Prevention_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                Symptoms_fab.visibility = View.VISIBLE
                About_fab.visibility = View.VISIBLE
                Infected_fab.visibility = View.VISIBLE
                Essentials_fab.visibility = View.VISIBLE
                //

                Tracker_fab.isClickable = true
                Symptoms_fab.isClickable = true
                About_fab.isClickable = true
                Infected_fab.isClickable = true
                Essentials_fab.isClickable = true
                ///////////

                Tracker_fab.isEnabled = true
                Symptoms_fab.isEnabled = true
                About_fab.isEnabled = true
                Infected_fab.isEnabled = true
                Essentials_fab.isEnabled = true
                isOpen = true
            }
            Tracker_fab.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            Symptoms_fab.setOnClickListener {
                val intent = Intent(this, SymptomActivity::class.java)
                startActivity(intent)
            }
            About_fab.setOnClickListener {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
            Infected_fab.setOnClickListener {
                val intent = Intent(this, InfectedActivity::class.java)
                startActivity(intent)
            }
            Essentials_fab.setOnClickListener {
                val intent = Intent(this, EssentialActivity::class.java)
                startActivity(intent)
            }
        }
/////////////////////
        ///////////////////////////////
        // init views
        mViewPager = findViewById(R.id.mViewPager)
        homeBtn = findViewById(R.id.homeBtn)
        addBtn = findViewById(R.id.addBtn)
        profileBtn = findViewById(R.id.profileBtn)
        searchBtn = findViewById(R.id.searchBtn)
        notiBtn = findViewById(R.id.notiBtn)


        //onclick listner

        homeBtn.setOnClickListener {
            mViewPager.currentItem = 0

        }

        searchBtn.setOnClickListener {

            mViewPager.currentItem = 1

        }

        addBtn.setOnClickListener {
            mViewPager.currentItem = 2

        }

        notiBtn.setOnClickListener {
            mViewPager.currentItem = 3

        }

        profileBtn.setOnClickListener {
            mViewPager.currentItem = 4

        }




        mPreventionPagerViewAdapter =
            PreventionPagerViewAdapter(
                supportFragmentManager
            )
        mViewPager.adapter = mPreventionPagerViewAdapter
        mViewPager.offscreenPageLimit = 5



        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })




        mViewPager.currentItem = 0
        homeBtn.setImageResource(R.drawable.ic_home_pink)





    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            homeBtn.setImageResource(R.drawable.ic_home_pink)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            addBtn.setImageResource(R.drawable.ic_add_black)
            notiBtn.setImageResource(R.drawable.ic_notifications_blck)
            profileBtn.setImageResource(R.drawable.ic_person_outline_)




        }
        if (position == 1) {
            homeBtn.setImageResource(R.drawable.ic_home_black_)
            searchBtn.setImageResource(R.drawable.ic_search_pink)
            addBtn.setImageResource(R.drawable.ic_add_black)
            notiBtn.setImageResource(R.drawable.ic_notifications_blck)
            profileBtn.setImageResource(R.drawable.ic_person_outline_)




        }
        if (position == 2) {
            homeBtn.setImageResource(R.drawable.ic_home_black_)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            addBtn.setImageResource(R.drawable.ic_add_pink)
            notiBtn.setImageResource(R.drawable.ic_notifications_blck)
            profileBtn.setImageResource(R.drawable.ic_person_outline_)

        }
        if (position == 3) {
            homeBtn.setImageResource(R.drawable.ic_home_black_)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            addBtn.setImageResource(R.drawable.ic_add_black)
            notiBtn.setImageResource(R.drawable.ic_notifications_fill)
            profileBtn.setImageResource(R.drawable.ic_person_outline_)

        }
        if (position == 4) {
            homeBtn.setImageResource(R.drawable.ic_home_black_)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            addBtn.setImageResource(R.drawable.ic_add_black)
            notiBtn.setImageResource(R.drawable.ic_notifications_blck)
            profileBtn.setImageResource(R.drawable.ic_person_pink_fill)

        }




    }
}