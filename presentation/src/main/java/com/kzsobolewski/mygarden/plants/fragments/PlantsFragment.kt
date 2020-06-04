package com.kzsobolewski.mygarden.plants.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.OnItemClickListener
import com.kzsobolewski.mygarden.plants.adapters.PlantListAdapter
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.android.synthetic.main.fragment_plants.*
import org.koin.android.viewmodel.ext.android.viewModel


class PlantsFragment : Fragment(), OnItemClickListener<Plant> {

    private val viewModel by viewModel<PlantsViewModel>()
    private val plantsAdapter = PlantListAdapter(this)

    private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onMoved(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            fromPos: Int,
            target: RecyclerView.ViewHolder,
            toPos: Int,
            x: Int,
            y: Int
        ) {
            val newList = plantsAdapter.currentList.toMutableList()
            val movedItem = newList[fromPos]
            val targetItem = newList[toPos]
            newList[fromPos] = targetItem
            newList[toPos] = movedItem
            plantsAdapter.submitList(newList)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    }


    companion object {
        fun newInstance(): PlantsFragment {
            val args = Bundle()
            val fragment = PlantsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPlants { }
        loadPlantsToRecyclerView()
        handleSwipeRefresh()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_plants, menu)
    }


    private fun loadPlantsToRecyclerView() {
        viewModel.plants.observe(viewLifecycleOwner, Observer { plants ->
            plants_recycler.apply {
                adapter = plantsAdapter
                ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this)
            }
            plants?.let {
                plantsAdapter.submitList(it)
            }
        })
    }

    private fun handleSwipeRefresh() {
        swipeRefreshView.setOnRefreshListener {
            viewModel.loadPlants { isLoaded ->
                if (isLoaded)
                    swipeRefreshView.isRefreshing = false
            }
        }
    }

    override fun onItemClick(item: Plant) {
        val bundle = bundleOf("key" to item)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_tabsFragment_to_plantInfoFragment, bundle)
    }
}



