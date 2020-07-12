package com.rt.apps.covid_19.About

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rt.apps.covid_19.About.AboutFragments.CauseOfSpreadFragment
import com.rt.apps.covid_19.About.AboutFragments.protectYourselfFromCovidFragment
import com.rt.apps.covid_19.About.AboutFragments.treatmentOfCovidFragment
import com.rt.apps.covid_19.About.AboutFragments.whatiscovidFragment


internal class AboutPagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                whatiscovidFragment()
            }
            1 -> {
                protectYourselfFromCovidFragment()
            }
            2 -> {
                CauseOfSpreadFragment()
            }
            3 -> {
                treatmentOfCovidFragment()
            }

            else -> whatiscovidFragment()
        }
    }

    override fun getCount(): Int {

        return 4
    }

}