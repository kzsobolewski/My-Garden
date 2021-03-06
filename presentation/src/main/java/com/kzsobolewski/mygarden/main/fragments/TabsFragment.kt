package com.kzsobolewski.mygarden.main.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentTabsBinding
import com.kzsobolewski.mygarden.main.adapters.TabsPagerAdapter
import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding
    private val viewModel
            by viewModel<TabsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = ""
        handleFabClick(view)
        initializeViewPagerAdapter()
        attachTabLayoutMediator()
        listenForViewPagerCallbacks()
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
                }
                else -> {
                    binding.newPlantFab.hide()
                }
            }
        }
    }


}
