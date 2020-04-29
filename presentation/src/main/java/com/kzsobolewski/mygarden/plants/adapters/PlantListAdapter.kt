package com.kzsobolewski.mygarden.plants.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.PlantItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.plant_item.view.*

class PlantListAdapter(
    private val onPlantListener: iOnPlantListener,
    private var cachedPlants: List<Plant> = listOf<Plant>()
) :
    RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {


    internal fun setPlants(plants: List<Plant>) {
        cachedPlants = plants
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cachedPlants[position], onPlantListener)
        loadImageToView(holder, position)
    }

    private fun loadImageToView(holder: ViewHolder, position: Int) {
        Picasso.get().apply {
            isLoggingEnabled = true
            load(cachedPlants[position].thumbnail)
                .placeholder(R.drawable.sample_logo)
                .error(R.drawable.ic_launcher_background)
                .into(holder.itemView.plant_image)
        }
    }


    override fun getItemCount(): Int = cachedPlants.size

    inner class ViewHolder(private val binding: PlantItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var onPlantListener: iOnPlantListener

        fun bind(item: Plant, onPlantListener: iOnPlantListener) {
            binding.item = item
            binding.executePendingBindings()
            this.onPlantListener = onPlantListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onPlantListener.OnPlantClick(adapterPosition)
        }
    }
}
