package com.example.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter (fm: FragmentManager, private val numsOfTabs: Int):
    FragmentStatePagerAdapter(fm, numsOfTabs) {


    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> return MyFragment1()
            1 -> return MyFragment2()
        }
        return MyFragment1()
    }

    override fun getCount(): Int {
        return numsOfTabs
    }


}