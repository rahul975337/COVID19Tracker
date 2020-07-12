package com.rt.apps.covid_19.Symptoms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.rt.apps.covid_19.About.AboutActivity
import com.rt.apps.covid_19.Essentials.EssentialActivity
import com.rt.apps.covid_19.Prevention.PreventionActivity
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.tracker.MainActivity
import kotlinx.android.synthetic.main.activity_main.About_fab
import kotlinx.android.synthetic.main.activity_main.Essentials_fab
import kotlinx.android.synthetic.main.activity_main.Prevention_fab
import kotlinx.android.synthetic.main.activity_main.Symptoms_fab
import kotlinx.android.synthetic.main.activity_main.Tracker_fab
import kotlinx.android.synthetic.main.activity_symptom.*

class SymptomActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false
    private lateinit var coughBtn: ImageButton
    private lateinit var feverBtn: ImageButton
    private lateinit var headacheBtn: ImageButton
    private lateinit var runnyNoseBtn: ImageButton
    private lateinit var soreThroatBtn: ImageButton

    private lateinit var weaknessBtn: ImageButton


    private lateinit var sViewPager: ViewPager
    private lateinit var sSymptomsPagerViewAdapter: SymptomsPagerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptom)
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
        Symptoms_fab.setOnClickListener {
            if (isOpen) {
                Tracker_fab.startAnimation(fabClose)
                About_fab.startAnimation(fabClose)
                Essentials_fab.startAnimation(fabClose)
                Prevention_fab.startAnimation(fabClose)


                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                About_fab.visibility = View.INVISIBLE
                Essentials_fab.visibility = View.INVISIBLE
                Prevention_fab.visibility = View.INVISIBLE

                //clickable

                Tracker_fab.isClickable = false
                About_fab.isClickable = false
                Essentials_fab.isClickable = false
                Prevention_fab.isClickable = false

                //disabling when fab close

                Tracker_fab.isEnabled = false
                About_fab.isEnabled = false
                Essentials_fab.isEnabled = false
                Prevention_fab.isEnabled = false
                lin.visibility = View.VISIBLE

                Symptoms_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                About_fab.startAnimation(fabOpen)
                Essentials_fab.startAnimation(fabOpen)
                Prevention_fab.startAnimation(fabOpen)

                Symptoms_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                About_fab.visibility = View.VISIBLE
                Essentials_fab.visibility = View.VISIBLE
                Prevention_fab.visibility = View.VISIBLE

                //

                Tracker_fab.isClickable = true
                About_fab.isClickable = true
                Essentials_fab.isClickable = true
                Prevention_fab.isClickable = true

                ///////////

                Tracker_fab.isEnabled = true
                About_fab.isEnabled = true
                Essentials_fab.isEnabled = true
                Prevention_fab.isEnabled = true
                lin.visibility = View.GONE
                isOpen = true
            }
            Tracker_fab.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
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
            Prevention_fab.setOnClickListener {
                val intent = Intent(this, PreventionActivity::class.java)
                startActivity(intent)
            }

        }
/////////////////////
        ///////////////////////////////
        // init views
        sViewPager = findViewById(R.id.sViewPager)
        coughBtn = findViewById(R.id.coughBtn)
        feverBtn = findViewById(R.id.feverBtn)
        headacheBtn = findViewById(R.id.headacheBtn)
        soreThroatBtn = findViewById(R.id.soreThroatBtn)
        runnyNoseBtn = findViewById(R.id.runnyNoseBtn)
        weaknessBtn = findViewById(R.id.weaknessBtn)


        //onclick listner

        coughBtn.setOnClickListener {
            sViewPager.currentItem = 0

        }

        feverBtn.setOnClickListener {

            sViewPager.currentItem = 1

        }

        headacheBtn.setOnClickListener {
            sViewPager.currentItem = 2

        }

        runnyNoseBtn.setOnClickListener {
            sViewPager.currentItem = 3

        }
        soreThroatBtn.setOnClickListener {
            sViewPager.currentItem = 4

        }
        weaknessBtn.setOnClickListener {
            sViewPager.currentItem = 5

        }






        sSymptomsPagerViewAdapter =
            SymptomsPagerViewAdapter(
                supportFragmentManager
            )
        sViewPager.adapter = sSymptomsPagerViewAdapter
        sViewPager.offscreenPageLimit = 6



        sViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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




        sViewPager.currentItem = 0
        coughBtn.setImageResource(R.drawable.glowing_dot)


    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            coughBtn.setImageResource(R.drawable.glowing_dot)
            feverBtn.setImageResource(R.drawable.dark_dot)
            headacheBtn.setImageResource(R.drawable.dark_dot)
            runnyNoseBtn.setImageResource(R.drawable.dark_dot)
            soreThroatBtn.setImageResource(R.drawable.dark_dot)

            weaknessBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 1) {
            coughBtn.setImageResource(R.drawable.dark_dot)
            feverBtn.setImageResource(R.drawable.glowing_dot)
            headacheBtn.setImageResource(R.drawable.dark_dot)
            runnyNoseBtn.setImageResource(R.drawable.dark_dot)
            soreThroatBtn.setImageResource(R.drawable.dark_dot)

            weaknessBtn.setImageResource(R.drawable.dark_dot)

        }
        if (position == 2) {
            coughBtn.setImageResource(R.drawable.dark_dot)
            feverBtn.setImageResource(R.drawable.dark_dot)
            headacheBtn.setImageResource(R.drawable.glowing_dot)
            runnyNoseBtn.setImageResource(R.drawable.dark_dot)
            soreThroatBtn.setImageResource(R.drawable.dark_dot)

            weaknessBtn.setImageResource(R.drawable.dark_dot)

        }
        if (position == 3) {
            coughBtn.setImageResource(R.drawable.dark_dot)
            feverBtn.setImageResource(R.drawable.dark_dot)
            headacheBtn.setImageResource(R.drawable.dark_dot)
            runnyNoseBtn.setImageResource(R.drawable.glowing_dot)
            soreThroatBtn.setImageResource(R.drawable.dark_dot)

            weaknessBtn.setImageResource(R.drawable.dark_dot)
        }
        if (position == 4) {
            coughBtn.setImageResource(R.drawable.dark_dot)
            feverBtn.setImageResource(R.drawable.dark_dot)
            headacheBtn.setImageResource(R.drawable.dark_dot)
            runnyNoseBtn.setImageResource(R.drawable.dark_dot)
            soreThroatBtn.setImageResource(R.drawable.glowing_dot)

            weaknessBtn.setImageResource(R.drawable.dark_dot)
        }
        if (position == 5) {
            coughBtn.setImageResource(R.drawable.dark_dot)
            feverBtn.setImageResource(R.drawable.dark_dot)
            headacheBtn.setImageResource(R.drawable.dark_dot)
            runnyNoseBtn.setImageResource(R.drawable.dark_dot)
            soreThroatBtn.setImageResource(R.drawable.dark_dot)

            weaknessBtn.setImageResource(R.drawable.glowing_dot)
        }


    }
}