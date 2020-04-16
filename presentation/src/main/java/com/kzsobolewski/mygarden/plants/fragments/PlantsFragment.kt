package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel
import com.kzsobolewski.mygarden.plants.adapters.PlatListAdapter
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.android.synthetic.main.fragment_plants.*
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
        
        showData()
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    private fun showData() {
        viewModel.plants.observe(viewLifecycleOwner, Observer {
            Log.d("debug", it.toString())
        })
    }


    private var dummyPlants = listOf(
        Plant("Plant 1", "something"),
        Plant("Plant 2", "something"),
        Plant("Plant 3", "something"),
        Plant("Plant 4", "something"),
        Plant("Plant 5", "something"),
        Plant("Plant 6", "something"),
        Plant("Plant 7", "something")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plants_recycler.adapter = PlatListAdapter(dummyPlants)
    }
}
