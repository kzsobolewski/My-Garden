package com.kzsobolewski.mygarden.main.fragments


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentTabsBinding
import com.kzsobolewski.mygarden.main.activities.MainActivity
import com.kzsobolewski.mygarden.main.adapters.TabsPagerAdapter


class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tabs, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setSearchButtonVisibility(false)
        handleFabClick(view)
        initializeViewPagerAdapter()
        attachTabLayoutMediator()
        listenForViewPagerCallbacks()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_option -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_tabsFragment_to_settingsFragment)
                true
            }
            R.id.about_option -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_tabsFragment_to_aboutFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleFabClick(view: View) {
        binding.newPlantFab.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_tabsFragment_to_newPlantFragment)
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

    private fun listenForViewPagerCallbacks() {
        binding.mainViewPager.registerOnPageChangeCallback(viewPagerCallback)
    }

    private val viewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> {
                    binding.newPlantFab.show()
                    (activity as MainActivity).setSearchButtonVisibility(false)
                }
                else -> {
                    binding.newPlantFab.hide()
                    (activity as MainActivity).setSearchButtonVisibility(true)
                }
            }
        }
    }


}
