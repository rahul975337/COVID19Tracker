package com.rt.apps.covid_19.Prevention

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rt.apps.covid_19.Prevention.PreventionFragments.*


internal class PreventionPagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MaskFragment()
            }
            1 -> {
                washFragment()
            }
            2 -> {
                distanceFragment()
            }
            3 -> {
                coverFragment()
            }
            4 -> {
                homeFragment()
            }
            5 -> {
                exerciseFragment()
            }
            6 -> {
                immuneFragment()
            }
            7 -> {
                consultFragment()
            }
            else -> MaskFragment()
        }
    }

    override fun getCount(): Int {

        return 8
    }

}