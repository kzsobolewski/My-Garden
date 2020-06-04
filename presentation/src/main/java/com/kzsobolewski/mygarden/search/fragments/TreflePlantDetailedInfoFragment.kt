package com.kzsobolewski.mygarden.search.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.databinding.FragmentTreflePlantDetailedInfoBinding
import com.kzsobolewski.mygarden.main.activities.MainActivity
import com.kzsobolewski.mygarden.main.fragments.INavigationFragment
import com.kzsobolewski.mygarden.search.viewmodels.TreflePlantDetailedInfoViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TreflePlantDetailedInfoFragment : Fragment(), INavigationFragment {

    private val viewModel
            by viewModel<TreflePlantDetailedInfoViewModel>()
    override val mainActivityForNavigation: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTreflePlantDetailedInfoBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    //TODO saveargs
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val treflePlant = arguments?.getParcelable<TreflePlant>("SEARCH_KEY")
        if (treflePlant != null) {
            viewModel.loadData(treflePlant.id)
        }
        (activity as MainActivity).setToolbarForSideScreen(treflePlant?.scientific_name)
    }

}
