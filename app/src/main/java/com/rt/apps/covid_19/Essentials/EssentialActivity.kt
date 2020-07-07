package com.rt.apps.covid_19.Essentials

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.rt.apps.covid_19.About.AboutActivity
import com.rt.apps.covid_19.Infected.InfectedActivity
import com.rt.apps.covid_19.Prevention.PreventionActivity
import com.rt.apps.covid_19.R
import com.rt.apps.covid_19.Symptoms.SymptomActivity
import com.rt.apps.covid_19.tracker.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class EssentialActivity : AppCompatActivity() {
    //for fab//
    var isOpen = false
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
                Infected_fab.startAnimation(fabClose)

                //visibility//
                Tracker_fab.visibility = View.INVISIBLE
                Symptoms_fab.visibility = View.INVISIBLE
                About_fab.visibility = View.INVISIBLE
                Prevention_fab.visibility = View.INVISIBLE
                Infected_fab.visibility = View.INVISIBLE
                //clickable

                Tracker_fab.isClickable = false
                Symptoms_fab.isClickable = false
                About_fab.isClickable = false
                Prevention_fab.isClickable = false
                Infected_fab.isClickable = false
                //disabling when fab close

                Tracker_fab.isEnabled = false
                Symptoms_fab.isEnabled = false
                About_fab.isEnabled = false
                Prevention_fab.isEnabled = false
                Infected_fab.isEnabled = false

                Essentials_fab.startAnimation(fabRClockwise)
                isOpen = false

            } else {


                Tracker_fab.startAnimation(fabOpen)
                Symptoms_fab.startAnimation(fabOpen)
                About_fab.startAnimation(fabOpen)
                Prevention_fab.startAnimation(fabOpen)
                Infected_fab.startAnimation(fabOpen)
                Essentials_fab.startAnimation(fabRAntiClockwise)
                ////

                Tracker_fab.visibility = View.VISIBLE
                Symptoms_fab.visibility = View.VISIBLE
                About_fab.visibility = View.VISIBLE
                Prevention_fab.visibility = View.VISIBLE
                Infected_fab.visibility = View.VISIBLE
                //

                Tracker_fab.isClickable = true
                Symptoms_fab.isClickable = true
                About_fab.isClickable = true
                Prevention_fab.isClickable = true
                Infected_fab.isClickable = true
                ///////////

                Tracker_fab.isEnabled = true
                Symptoms_fab.isEnabled = true
                About_fab.isEnabled = true
                Prevention_fab.isEnabled = true
                Infected_fab.isEnabled = true
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
            Infected_fab.setOnClickListener {
                val intent = Intent(this, InfectedActivity::class.java)
                startActivity(intent)
            }
        }
/////////////////////
    }
}