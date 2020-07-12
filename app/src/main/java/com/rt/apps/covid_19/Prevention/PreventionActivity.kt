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
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.Symptoms.SymptomActivity
import com.rt.apps.covid_19.tracker.MainActivity
import kotlinx.android.synthetic.main.activity_main.About_fab
import kotlinx.android.synthetic.main.activity_main.Essentials_fab
import kotlinx.android.synthetic.main.activity_main.Prevention_fab
import kotlinx.android.synthetic.main.activity_main.Symptoms_fab
import kotlinx.android.synthetic.main.activity_main.Tracker_fab
import kotlinx.android.synthetic.main.activity_prevention.*

class PreventionActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false
    private lateinit var maskBtn: ImageButton
    private lateinit var distanceBtn: ImageButton
    private lateinit var coverBtn: ImageButton
    private lateinit var washBtn: ImageButton
    private lateinit var homeBtn: ImageButton

    private lateinit var pViewPager: ViewPager
    private lateinit var pPreventionPagerViewAdapter: PreventionPagerViewAdapter
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
                Essentials_fab.startAnimation(fabClose)

                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                Symptoms_fab.visibility = View.INVISIBLE
                About_fab.visibility = View.INVISIBLE
                Essentials_fab.visibility = View.INVISIBLE
                //clickable

                Tracker_fab.isClickable = false
                Symptoms_fab.isClickable = false
                About_fab.isClickable = false
                Essentials_fab.isClickable = false
                //disabling when fab close

                Tracker_fab.isEnabled = false
                Symptoms_fab.isEnabled = false
                About_fab.isEnabled = false
                Essentials_fab.isEnabled = false
////////////////////////
                Prevention_fab.startAnimation(fabRClockwise)
                /////////
                lin.visibility = View.VISIBLE
                //////////
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                Symptoms_fab.startAnimation(fabOpen)
                About_fab.startAnimation(fabOpen)
                Essentials_fab.startAnimation(fabOpen)
                ////
                Prevention_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                Symptoms_fab.visibility = View.VISIBLE
                About_fab.visibility = View.VISIBLE
                Essentials_fab.visibility = View.VISIBLE
                //

                Tracker_fab.isClickable = true
                Symptoms_fab.isClickable = true
                About_fab.isClickable = true
                Essentials_fab.isClickable = true
                ///////////

                Tracker_fab.isEnabled = true
                Symptoms_fab.isEnabled = true
                About_fab.isEnabled = true
                Essentials_fab.isEnabled = true
                //////////
                lin.visibility = View.GONE
                //////////
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

            Essentials_fab.setOnClickListener {
                val intent = Intent(this, EssentialActivity::class.java)
                startActivity(intent)
            }
        }
/////////////////////
        ///////////////////////////////
        // init views
        pViewPager = findViewById(R.id.pViewPager)
        maskBtn = findViewById(R.id.maskBtn)
        distanceBtn = findViewById(R.id.distanceBtn)
        homeBtn = findViewById(R.id.homeBtn)
        washBtn = findViewById(R.id.washBtn)
        coverBtn = findViewById(R.id.coverBtn)


        //onclick listner

        maskBtn.setOnClickListener {
            pViewPager.currentItem = 0

        }

        washBtn.setOnClickListener {

            pViewPager.currentItem = 1

        }

        distanceBtn.setOnClickListener {
            pViewPager.currentItem = 2

        }

        coverBtn.setOnClickListener {
            pViewPager.currentItem = 3

        }

        homeBtn.setOnClickListener {
            pViewPager.currentItem = 4

        }




        pPreventionPagerViewAdapter =
            PreventionPagerViewAdapter(
                supportFragmentManager
            )
        pViewPager.adapter = pPreventionPagerViewAdapter
        pViewPager.offscreenPageLimit = 5



        pViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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




        pViewPager.currentItem = 0
        maskBtn.setImageResource(R.drawable.glowing_dot)





    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            maskBtn.setImageResource(R.drawable.glowing_dot)
            washBtn.setImageResource(R.drawable.dark_dot)
            distanceBtn.setImageResource(R.drawable.dark_dot)
            coverBtn.setImageResource(R.drawable.dark_dot)
            homeBtn.setImageResource(R.drawable.dark_dot)



        }
        if (position == 1) {
            maskBtn.setImageResource(R.drawable.dark_dot)
            washBtn.setImageResource(R.drawable.glowing_dot)
            distanceBtn.setImageResource(R.drawable.dark_dot)
            coverBtn.setImageResource(R.drawable.dark_dot)
            homeBtn.setImageResource(R.drawable.dark_dot)



        }
        if (position == 2) {
            maskBtn.setImageResource(R.drawable.dark_dot)
            washBtn.setImageResource(R.drawable.dark_dot)
            distanceBtn.setImageResource(R.drawable.glowing_dot)
            coverBtn.setImageResource(R.drawable.dark_dot)
            homeBtn.setImageResource(R.drawable.dark_dot)

        }
        if (position == 3) {
            maskBtn.setImageResource(R.drawable.dark_dot)
            washBtn.setImageResource(R.drawable.dark_dot)
            distanceBtn.setImageResource(R.drawable.dark_dot)
            coverBtn.setImageResource(R.drawable.glowing_dot)
            homeBtn.setImageResource(R.drawable.dark_dot)

        }
        if (position == 4) {
            maskBtn.setImageResource(R.drawable.dark_dot)
            washBtn.setImageResource(R.drawable.dark_dot)
            distanceBtn.setImageResource(R.drawable.dark_dot)
            coverBtn.setImageResource(R.drawable.dark_dot)
            homeBtn.setImageResource(R.drawable.glowing_dot)

        }




    }
}