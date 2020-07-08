package com.rt.apps.covid_19.Essentials

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rt.apps.covid_19.Essentials.EssentialsFragments.GeneralFragment
import com.rt.apps.covid_19.Essentials.EssentialsFragments.MarketFragment
import com.rt.apps.covid_19.Essentials.EssentialsFragments.MedicalFragment
import com.rt.apps.covid_19.Prevention.PreventionFragments.*


internal class EssentialPagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                GeneralFragment()
            }
            1 -> {
               MarketFragment()
            }
            2 -> {
                MedicalFragment()
            }

            else -> GeneralFragment()
        }
    }

    override fun getCount(): Int {

        return 3
    }

}