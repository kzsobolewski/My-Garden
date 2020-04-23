package com.kzsobolewski.mygarden.plants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.PlantItemBinding
import com.squareup.picasso.Picasso

class PlantListAdapter() :
    RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {

    private var cachedPlants: List<Plant> = listOf<Plant>()

    internal fun setPlants(plants: List<Plant>) {
        cachedPlants = plants
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cachedPlants[position])
    }

    override fun getItemCount(): Int = cachedPlants.size

    inner class ViewHolder(private val binding: PlantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.item = item
            Picasso.get().apply {
                isLoggingEnabled = true
                load("https://pngimage.net/wp-content/uploads/2018/06/png-small.png")
                    .placeholder(R.drawable.sample_logo)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.plantImage)
            }
            binding.executePendingBindings()
        }
    }
}