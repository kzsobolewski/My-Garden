package com.kzsobolewski.mygarden.plants.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.adapters.PlatListAdapter

class PlantsFragment : Fragment() {


    companion object {
        fun newInstance() =
            PlantsFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantsRecycler = view.findViewById<RecyclerView>(R.id.plats_recycler)
        plantsRecycler.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter  = PlatListAdapter(context)
        }
    }
}
