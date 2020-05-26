package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.FragmentPlantInfoBinding
import com.kzsobolewski.mygarden.main.activities.MainActivity
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import com.kzsobolewski.mygarden.plants.viewmodels.PlantInfoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PlantInfoFragment : Fragment(), INavigationFragment {

    val viewModel by viewModel<PlantInfoViewModel>()
    override val mainActivity: MainActivity
        get() = activity as MainActivity

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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadPlant(arguments?.getParcelable("key")!!)
        (activity as MainActivity).setToolbarForSideScreen(viewModel.getPlantName())
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_plants_info, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_delete_button -> {
                viewModel.deleteCurrentPlant()
                viewModel.isDeleted.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        Navigation.findNavController(requireView()).popBackStack()
                        onBackPressed()
                    }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
