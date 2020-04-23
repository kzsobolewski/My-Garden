package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentNewPlantBinding
import com.kzsobolewski.mygarden.main.fragments.BaseFragment
import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewPlantFragment : BaseFragment<FragmentNewPlantBinding>() {

    val viewModel by viewModel<NewPlantViewModel>()

    override val layoutId: Int = R.layout.fragment_new_plant

    companion object {
        fun newInstance(): NewPlantFragment {
            val args = Bundle()
            val fragment = NewPlantFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plantSaved.observe(viewLifecycleOwner, Observer { saved ->
            if (saved) {
                Navigation.findNavController(view).popBackStack()
            }
        })
    }

}