package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.PlantListAdapter
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.android.synthetic.main.fragment_plants.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class PlantsFragment : Fragment() {

    val viewModel by viewModel<PlantsViewModel>()

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
        lifecycleScope.launch {
            val value = viewModel.loadPlants().await()
            if(!value)
                Toast.makeText(requireContext(), "error ocurred", Toast.LENGTH_LONG).show()
        }

        val adapter = PlantListAdapter()
        viewModel.plants.observe(viewLifecycleOwner, Observer { plants ->
            plants?.let {
                adapter.setPlants(it)
                adapter.notifyDataSetChanged()
            }

            //swipeRefreshView.isRefreshing = false
        })
        plants_recycler.adapter = adapter

        swipeRefreshView.setOnRefreshListener {
            lifecycleScope.launch {
                // moze lepiej by bylo podawac callback jako argument metody loadPlants()
                val value = viewModel.loadPlants().await()
                swipeRefreshView.isRefreshing = false
            }
        }
    }
}
