package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.mygarden.databinding.FragmentPlantInfoBinding
import com.kzsobolewski.mygarden.main.activities.MainActivity
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import com.kzsobolewski.mygarden.plants.viewmodels.PlantInfoViewModel
import kotlinx.android.synthetic.main.fragment_plant_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlantInfoFragment : Fragment(), INavigationFragment {

    val viewModel by viewModel<PlantInfoViewModel>()

    companion object {
        fun newInstance(): PlantInfoFragment {
            val args = Bundle()
            val fragment = PlantInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding =
            FragmentPlantInfoBinding
                .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (activity as MainActivity).apply {
            setUpNavigationVisibility(true)
            setLogoVisibility(false)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val plant = arguments?.getParcelable<Plant>("key")

        delete_plant_button.setOnClickListener {
            if (plant?.id != null) {
                viewModel.deletePlant(plant.id)
                Navigation.findNavController(requireView()).popBackStack()
            }
        }

        info_name.text = plant?.name ?: ""
        info_description.text = plant?.description ?: ""
        info_hydration.text = plant?.hydration.toString()
    }

    override fun onBackPressed(): Boolean {
        (activity as MainActivity).apply {
            setUpNavigationVisibility(false)
            setLogoVisibility(true)
        }
        return false
    }

}
