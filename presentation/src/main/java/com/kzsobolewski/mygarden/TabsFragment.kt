package com.kzsobolewski.mygarden


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TabsFragment : Fragment() {

    private lateinit var adapter: TabsPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViewPagerAdapter(view)
        attachTabLayoutMediator(view)
    }

    private fun initializeViewPagerAdapter(view: View){
        adapter = TabsPagerAdapter(this)
        viewPager = view.findViewById(R.id.main_view_pager)
        viewPager.adapter = adapter
    }

    private fun attachTabLayoutMediator(view : View) {
        val tabLayout: TabLayout = view.findViewById(R.id.main_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.plants_tab)
                else -> getString(R.string.search_tab)
            }
        }.attach()
    }

}
