package com.kzsobolewski.mygarden.plants.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.mygarden.databinding.FragmentNewPlantBinding
import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewPlantFragment : Fragment() {

    val viewModel by viewModel<NewPlantViewModel>()

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
        val binding =
            FragmentNewPlantBinding
                .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            //TODO listener for popbackstack in toolbar
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isPlantSaved.observe(viewLifecycleOwner, Observer { saved ->
            if (saved) {
                Navigation.findNavController(view).popBackStack()
                hideKeyboard()
            }
        })
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}