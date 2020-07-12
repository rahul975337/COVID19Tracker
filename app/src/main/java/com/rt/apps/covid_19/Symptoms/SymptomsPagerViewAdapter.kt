package com.rt.apps.covid_19.Symptoms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rt.apps.covid_19.Symptoms.SymptomFragments.*


internal class SymptomsPagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CoughFragment()
            }
            1 -> {
                FeverFragment()
            }
            2 -> {
                HeadacheFragment()
            }
            3 -> {
                RunnyNoseFragment()
            }
            4 -> {

                SoreThroatFragment()
            }
            5 -> {
                WeaknessFragment()
            }


            else -> CoughFragment()
        }
    }

    override fun getCount(): Int {

        return 6
    }

}