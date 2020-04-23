package com.kzsobolewski.mygarden.main.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.main.viewmodels.PlantInfoViewModel

class PlantInfoFragment : Fragment() {

    companion object {
        fun newInstance() = PlantInfoFragment()
    }

    private lateinit var viewModel: PlantInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlantInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
