package com.example.settingssample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settingssample.databinding.ListItemBinding
import com.example.settingssample.model.Root

class CustomListAdapter(val items:List<Root>): RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
       return items.size
    }

    inner  class ViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Root) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}