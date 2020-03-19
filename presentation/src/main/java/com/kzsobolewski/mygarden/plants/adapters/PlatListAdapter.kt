package com.kzsobolewski.mygarden.plants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.mygarden.databinding.PlantItemBinding
import com.kzsobolewski.domain.Plant

class PlatListAdapter(var Plants: List<Plant>) :
    RecyclerView.Adapter<PlatListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(Plants[position])

    override fun getItemCount(): Int = Plants.size

    inner class ViewHolder(private val binding: PlantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}
