package com.ariefrahmanfajri.searchapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ariefrahmanfajri.searchapp.databinding.SearchItemBinding

class SearchAdapter(private val list: List<String>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(private val binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvName.text = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val view = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}