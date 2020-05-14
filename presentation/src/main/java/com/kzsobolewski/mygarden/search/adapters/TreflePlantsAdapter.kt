package com.kzsobolewski.mygarden.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.databinding.TrefleItemBinding
import com.kzsobolewski.mygarden.plants.adapters.OnItemClickListener

class TreflePlantsAdapter(
    private var clickable: OnItemClickListener<TreflePlant>,
    private var cachedPlants: List<TreflePlant> = listOf()
) :
    RecyclerView.Adapter<TreflePlantsAdapter.ViewHolder>() {

    internal fun setPlants(items: List<TreflePlant>) {
        cachedPlants = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TrefleItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cachedPlants[position], clickable)
    }

    override fun getItemCount() = cachedPlants.size

    inner class ViewHolder(private val binding: TrefleItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var clickable: OnItemClickListener<TreflePlant>

        fun bind(item: TreflePlant, clickable: OnItemClickListener<TreflePlant>) {
            binding.item = item
            binding.executePendingBindings()
            this.clickable = clickable
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickable.onItemClick(cachedPlants[adapterPosition])
        }
    }
}