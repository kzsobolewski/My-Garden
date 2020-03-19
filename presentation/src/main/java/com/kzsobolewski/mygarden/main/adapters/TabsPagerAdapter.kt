package com.kzsobolewski.mygarden.main.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kzsobolewski.mygarden.plants.fragments.PlantsFragment
import com.kzsobolewski.mygarden.search.fragments.SearchFragment


class TabsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PlantsFragment.newInstance()
            }
            else -> {
                SearchFragment.newInstance()
            }
        }
    }
}