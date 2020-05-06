package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.OnPlantListener
import com.kzsobolewski.mygarden.plants.adapters.PlantListAdapter
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.android.synthetic.main.fragment_plants.*
import org.koin.android.viewmodel.ext.android.viewModel


class PlantsFragment : Fragment(), OnPlantListener {

    val viewModel by viewModel<PlantsViewModel>()
    private var currentPlants: List<Plant> = listOf()

    companion object {
        fun newInstance(): PlantsFragment {
            val args = Bundle()
            val fragment = PlantsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPlants { }
        loadPlantsToRecyclerView()
        handleSwipeRefresh()
    }


    private fun loadPlantsToRecyclerView() {
        val adapter = PlantListAdapter(this)
        plants_recycler.adapter = adapter
        viewModel.plants.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                adapter.setPlants(it)
                currentPlants = it
            }
        })
    }

    private fun handleSwipeRefresh() {
        swipeRefreshView.setOnRefreshListener {
            viewModel.loadPlants { isLoaded ->
                if (isLoaded)
                    swipeRefreshView.isRefreshing = false
            }
        }
    }

    override fun onPlantClick(position: Int) {
        val bundle = bundleOf("key" to currentPlants[position])
        Navigation.findNavController(requireView())
            .navigate(R.id.action_tabsFragment_to_plantInfoFragment, bundle)
    }
}

