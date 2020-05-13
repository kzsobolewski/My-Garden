package com.kzsobolewski.mygarden.search.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.Clickable
import com.kzsobolewski.mygarden.search.adapters.TreflePlantsAdapter
import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), Clickable {

    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var adapter: TreflePlantsAdapter
    private var treflePlants: List<TreflePlant> = listOf()


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
        adapter = TreflePlantsAdapter(this)
        viewModel.plants.observe(viewLifecycleOwner, Observer {
            adapter.setPlants(it)
            treflePlants = it
        })
        trefle_plants_recycler.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val bundle = bundleOf("SEARCH_KEY" to treflePlants[position].id)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_tabsFragment_to_treflePlantDetailedInfoFragment, bundle)
    }
}




