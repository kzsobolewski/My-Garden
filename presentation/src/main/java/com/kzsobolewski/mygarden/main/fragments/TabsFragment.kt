package com.kzsobolewski.mygarden.main.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentTabsBinding
import com.kzsobolewski.mygarden.main.adapters.TabsPagerAdapter
import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel


class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tabs, container, false)
        binding.viewModel = TabsViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViewPagerAdapter()
        attachTabLayoutMediator()
        listenForViewPagerCallbacks()
    }

    private fun listenForViewPagerCallbacks() {
        binding.mainViewPager.registerOnPageChangeCallback(ViewPagerCallback())
    }

    inner class ViewPagerCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> binding.newPlantFab.show()
                else -> binding.newPlantFab.hide()
            }
        }
    }

    private fun initializeViewPagerAdapter() {
        binding.mainViewPager.adapter = TabsPagerAdapter(this)
    }

    private fun attachTabLayoutMediator() {
        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.plants_tab)
                else -> getString(R.string.search_tab)
            }
        }.attach()
    }
}
