package com.rt.apps.covid_19.About

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.rt.apps.covid_19.Essentials.EssentialActivity
import com.rt.apps.covid_19.Prevention.PreventionActivity
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.Symptoms.SymptomActivity
import com.rt.apps.covid_19.tracker.MainActivity
import kotlinx.android.synthetic.main.activity_main.About_fab
import kotlinx.android.synthetic.main.activity_main.Essentials_fab
import kotlinx.android.synthetic.main.activity_main.Prevention_fab
import kotlinx.android.synthetic.main.activity_main.Symptoms_fab
import kotlinx.android.synthetic.main.activity_main.Tracker_fab
import kotlinx.android.synthetic.main.activity_prevention.*

class AboutActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false

    ////////
    private lateinit var covidBtn: ImageButton
    private lateinit var protectYourselfBtn: ImageButton
    private lateinit var causeOfSpreadBtn: ImageButton
    private lateinit var treatmentBtn: ImageButton


    private lateinit var aViewPager: ViewPager
    private lateinit var aEssentialPagerViewAdapter: AboutPagerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
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
        About_fab.setOnClickListener {
            if (isOpen) {
                Tracker_fab.startAnimation(fabClose)
                Symptoms_fab.startAnimation(fabClose)
                Essentials_fab.startAnimation(fabClose)
                Prevention_fab.startAnimation(fabClose)


                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                Symptoms_fab.visibility = View.INVISIBLE
                Essentials_fab.visibility = View.INVISIBLE
                Prevention_fab.visibility = View.INVISIBLE

                //clickable

                Tracker_fab.isClickable = false
                Symptoms_fab.isClickable = false
                Essentials_fab.isClickable = false
                Prevention_fab.isClickable = false

                //disabling when fab close

                Tracker_fab.isEnabled = false
                Symptoms_fab.isEnabled = false
                Essentials_fab.isEnabled = false
                Prevention_fab.isEnabled = false
                lin.visibility = View.VISIBLE

                About_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                Symptoms_fab.startAnimation(fabOpen)
                Essentials_fab.startAnimation(fabOpen)
                Prevention_fab.startAnimation(fabOpen)

                About_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                Symptoms_fab.visibility = View.VISIBLE
                Essentials_fab.visibility = View.VISIBLE
                Prevention_fab.visibility = View.VISIBLE

                //

                Tracker_fab.isClickable = true
                Symptoms_fab.isClickable = true
                Essentials_fab.isClickable = true
                Prevention_fab.isClickable = true

                ///////////

                Tracker_fab.isEnabled = true
                Symptoms_fab.isEnabled = true
                Essentials_fab.isEnabled = true
                Prevention_fab.isEnabled = true
                lin.visibility = View.GONE
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
            Essentials_fab.setOnClickListener {
                val intent = Intent(this, EssentialActivity::class.java)
                startActivity(intent)
            }
            Prevention_fab.setOnClickListener {
                val intent = Intent(this, PreventionActivity::class.java)
                startActivity(intent)
            }

        }
/////////////////////
        ///////////////////////////////
        // init views
        aViewPager = findViewById(R.id.aViewPager)
        covidBtn = findViewById(R.id.covidBtn)
        protectYourselfBtn = findViewById(R.id.protectYourselfBtn)
        causeOfSpreadBtn = findViewById(R.id.causeOfSpreadBtn)
        treatmentBtn = findViewById(R.id.treatmentBtn)


        //onclick listner

        covidBtn.setOnClickListener {
            aViewPager.currentItem = 0

        }

        protectYourselfBtn.setOnClickListener {

            aViewPager.currentItem = 1

        }
        causeOfSpreadBtn.setOnClickListener {

            aViewPager.currentItem = 2

        }
        treatmentBtn.setOnClickListener {

            aViewPager.currentItem = 3

        }


        aEssentialPagerViewAdapter =
            AboutPagerViewAdapter(
                supportFragmentManager
            )
        aViewPager.adapter = aEssentialPagerViewAdapter
        aViewPager.offscreenPageLimit = 4



        aViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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




        aViewPager.currentItem = 0
        covidBtn.setImageResource(R.drawable.glowing_dot)


    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            covidBtn.setImageResource(R.drawable.glowing_dot)
            protectYourselfBtn.setImageResource(R.drawable.dark_dot)
            causeOfSpreadBtn.setImageResource(R.drawable.dark_dot)
            treatmentBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 1) {
            covidBtn.setImageResource(R.drawable.dark_dot)
            protectYourselfBtn.setImageResource(R.drawable.glowing_dot)
            causeOfSpreadBtn.setImageResource(R.drawable.dark_dot)
            treatmentBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 2) {
            covidBtn.setImageResource(R.drawable.dark_dot)
            protectYourselfBtn.setImageResource(R.drawable.dark_dot)
            causeOfSpreadBtn.setImageResource(R.drawable.glowing_dot)
            treatmentBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 3) {
            covidBtn.setImageResource(R.drawable.dark_dot)
            protectYourselfBtn.setImageResource(R.drawable.dark_dot)
            causeOfSpreadBtn.setImageResource(R.drawable.dark_dot)
            treatmentBtn.setImageResource(R.drawable.glowing_dot)


        }


    }
}