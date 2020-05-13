package com.kzsobolewski.mygarden.search.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.search.viewmodels.TreflePlantDetailedInfoViewModel
import kotlinx.android.synthetic.main.fragment_trefle_plant_detailed_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class TreflePlantDetailedInfoFragment : Fragment() {

    private val viewModel
            by viewModel<TreflePlantDetailedInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trefle_plant_detailed_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val plantId = arguments?.getInt("SEARCH_KEY")
        if (plantId != null) {
            viewModel.loadData(plantId)
            viewModel.plantDetails.observe(viewLifecycleOwner, Observer {
                common_name.text = it.toString()
            })
        }
    }

}
