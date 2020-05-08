package com.kzsobolewski.mygarden.search.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    val viewModel by viewModel<SearchViewModel>()

    companion object {
        fun newInstance(): SearchFragment {
            val args = Bundle()
            val fragment =
                SearchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPlants()
        viewModel.plants.observe(viewLifecycleOwner, Observer { plants ->
            Log.d("trefleResponse", plants[0].scientific_name)
        })
        search_button.setOnClickListener {

        }
    }
}
