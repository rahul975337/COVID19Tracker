package com.rt.apps.covid_19.Essentials

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.rt.apps.covid_19.About.AboutActivity
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

class EssentialActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false

    ////////
    private lateinit var generalBtn: ImageButton
    private lateinit var marketBtn: ImageButton
    private lateinit var medicalBtn: ImageButton

    private lateinit var eViewPager: ViewPager
    private lateinit var eEssentialPagerViewAdapter: EssentialPagerViewAdapter

    ////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essential)
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
        Essentials_fab.setOnClickListener {
            if (isOpen) {
                Tracker_fab.startAnimation(fabClose)
                Symptoms_fab.startAnimation(fabClose)
                About_fab.startAnimation(fabClose)
                Prevention_fab.startAnimation(fabClose)


                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                Symptoms_fab.visibility = View.INVISIBLE
                About_fab.visibility = View.INVISIBLE
                Prevention_fab.visibility = View.INVISIBLE

                //clickable

                Tracker_fab.isClickable = false
                Symptoms_fab.isClickable = false
                About_fab.isClickable = false
                Prevention_fab.isClickable = false

                //disabling when fab close

                Tracker_fab.isEnabled = false
                Symptoms_fab.isEnabled = false
                About_fab.isEnabled = false
                Prevention_fab.isEnabled = false


                Essentials_fab.startAnimation(fabRClockwise)
                lin.visibility = View.VISIBLE
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                Symptoms_fab.startAnimation(fabOpen)
                About_fab.startAnimation(fabOpen)
                Prevention_fab.startAnimation(fabOpen)

                Essentials_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                Symptoms_fab.visibility = View.VISIBLE
                About_fab.visibility = View.VISIBLE
                Prevention_fab.visibility = View.VISIBLE

                //

                Tracker_fab.isClickable = true
                Symptoms_fab.isClickable = true
                About_fab.isClickable = true
                Prevention_fab.isClickable = true

                ///////////

                Tracker_fab.isEnabled = true
                Symptoms_fab.isEnabled = true
                About_fab.isEnabled = true
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
            About_fab.setOnClickListener {
                val intent = Intent(this, AboutActivity::class.java)
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
        eViewPager = findViewById(R.id.eViewPager)
        generalBtn = findViewById(R.id.generalBtn)
        marketBtn = findViewById(R.id.marketBtn)
        medicalBtn = findViewById(R.id.medicalBtn)


        //onclick listner

        generalBtn.setOnClickListener {
            eViewPager.currentItem = 0

        }

        marketBtn.setOnClickListener {

            eViewPager.currentItem = 1

        }

        medicalBtn.setOnClickListener {
            eViewPager.currentItem = 2

        }






        eEssentialPagerViewAdapter =
            EssentialPagerViewAdapter(
                supportFragmentManager
            )
        eViewPager.adapter = eEssentialPagerViewAdapter
        eViewPager.offscreenPageLimit = 3



        eViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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




        eViewPager.currentItem = 0
        generalBtn.setImageResource(R.drawable.glowing_dot)


    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            generalBtn.setImageResource(R.drawable.glowing_dot)
            marketBtn.setImageResource(R.drawable.dark_dot)
            medicalBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 1) {
            generalBtn.setImageResource(R.drawable.dark_dot)
            marketBtn.setImageResource(R.drawable.glowing_dot)
            medicalBtn.setImageResource(R.drawable.dark_dot)


        }
        if (position == 2) {
            generalBtn.setImageResource(R.drawable.dark_dot)
            marketBtn.setImageResource(R.drawable.dark_dot)
            medicalBtn.setImageResource(R.drawable.glowing_dot)

        }


    }
}