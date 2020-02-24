package com.kzsobolewski.mygarden


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> { PlantsFragment.newInstance() }
            else -> { SearchFragment.newInstance() }
        }
    }
}