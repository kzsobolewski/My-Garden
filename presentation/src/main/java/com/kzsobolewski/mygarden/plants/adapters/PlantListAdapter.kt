package com.kzsobolewski.mygarden.plants.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.PlantItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.plant_item.view.*

class PlantListAdapter(
    private val onItemClick: OnItemClickListener<Plant>
) :
    ListAdapter<Plant, PlantListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
        loadImageToView(holder, position)
    }

    private fun loadImageToView(holder: ViewHolder, position: Int) {
        Picasso.get().apply {
            isLoggingEnabled = true
            load(getItem(position).thumbnail)
                .placeholder(R.drawable.default_plant)
                .into(holder.itemView.plant_image)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Plant>(){
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: PlantItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var onItemClick: OnItemClickListener<Plant>

        fun bind(item: Plant, onItemClickListener: OnItemClickListener<Plant>) {
            binding.item = item
            binding.executePendingBindings()
            this.onItemClick = onItemClickListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClick.onItemClick(getItem(adapterPosition))
        }
    }
}
