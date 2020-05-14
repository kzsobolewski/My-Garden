package com.kzsobolewski.mygarden.search.fragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.OnItemClickListener
import com.kzsobolewski.mygarden.search.adapters.TreflePlantsAdapter
import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), OnItemClickListener<TreflePlant> {

    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var adapter: TreflePlantsAdapter
    //nie chemy miec tu tego pola - powinn byc ono w viewModelu tylko
    //fragment nie powinien przechowywac danych
    //private var treflePlants: List<TreflePlant> = listOf()


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
            //treflePlants = it
        })
        trefle_plants_recycler.adapter = adapter
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        handleSearchView(menu.findItem(R.id.search_button))
    }

    private fun handleSearchView(item: MenuItem) {
        val searchView = item.actionView as? SearchView
        searchView?.apply {
            queryHint = "Write plant name"
            setOnQueryTextListener(searchViewListener)
        }
    }

    private val searchViewListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.d("search this", query)
            if (query != null)
                viewModel.loadPlants(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            Log.d("search this", newText)
            //ta logika powinna byc w viewmodelu
            // mozna dodac czyszczenie na pustym tekscie
            if (newText != null && newText.length >= 3)
                viewModel.loadPlants(newText)
            return false
        }
    }

    //a moze jeszcze uzyc safe args do nawigacji?
    override fun onItemClick(item: TreflePlant) {
        val bundle = bundleOf("SEARCH_KEY" to item.id)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_tabsFragment_to_treflePlantDetailedInfoFragment, bundle)
    }
}




