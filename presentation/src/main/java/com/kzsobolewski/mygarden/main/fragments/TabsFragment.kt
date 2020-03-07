package com.kzsobolewski.mygarden.main.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.kzsobolewski.mygarden.main.adapters.TabsPagerAdapter
import com.kzsobolewski.mygarden.R
import kotlinx.android.synthetic.main.fragment_tabs.*


class TabsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeViewPagerAdapter()
        attachTabLayoutMediator()
    }

    private fun initializeViewPagerAdapter(){
        main_view_pager.adapter = TabsPagerAdapter(this)
    }

    private fun attachTabLayoutMediator() {
        TabLayoutMediator(main_tab_layout, main_view_pager) {tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.plants_tab)
                else -> getString(R.string.search_tab)
            }
        }.attach()
    }
}
