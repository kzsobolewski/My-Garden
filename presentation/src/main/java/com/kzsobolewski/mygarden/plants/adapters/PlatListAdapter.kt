package com.kzsobolewski.mygarden.plants.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.mygarden.databinding.PlantItemBinding
import com.kzsobolewski.mygarden.plants.Plant
import kotlinx.android.synthetic.main.plant_item.view.*

class PlatListAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<PlatListAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dummyPlants[position])

    override fun getItemCount(): Int = dummyPlants.size

    inner class ViewHolder(val binding: PlantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
