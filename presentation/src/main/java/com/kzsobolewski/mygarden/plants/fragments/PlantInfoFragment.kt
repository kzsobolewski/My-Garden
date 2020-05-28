package com.kzsobolewski.mygarden.plants.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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

    val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_BATTERY_LOW){

            }
        }
    }

    override fun onStart() {
        super.onStart()
        context?.registerReceiver(myReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(myReceiver)
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
                //live data chcemy obserwować zazwyczaj tylko raz np w onViewCreated
                // tutaj może lepiej jednak zastosować callback?
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
