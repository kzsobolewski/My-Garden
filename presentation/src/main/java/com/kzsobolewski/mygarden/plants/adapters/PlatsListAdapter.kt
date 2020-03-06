package com.kzsobolewski.mygarden.plants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.Plant
import kotlinx.android.synthetic.main.item_plants.view.*

class PlatsListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<PlatsListAdapter.PlantItemViewHolder>() {

    private val  inflater = LayoutInflater.from(context)
    private var dummyPlants = listOf(
        Plant("1", "something"),
        Plant("2", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("3", "something"),
        Plant("4", "something")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PlantItemViewHolder {
        val itemView = inflater.inflate(R.layout.item_plants, parent, false)
        return PlantItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlantItemViewHolder, position: Int) {
        val currentItem = dummyPlants[position]
        holder.itemView.plant_name.text = currentItem.name
    }

    override fun getItemCount(): Int = dummyPlants.size

    inner class PlantItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val plantName : TextView = itemView.findViewById(R.id.plant_name)
    }
}
