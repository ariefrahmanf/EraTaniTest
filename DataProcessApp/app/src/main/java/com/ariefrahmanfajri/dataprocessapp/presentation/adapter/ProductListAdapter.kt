package com.ariefrahmanfajri.dataprocessapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ariefrahmanfajri.dataprocessapp.databinding.ProductItemBinding
import com.ariefrahmanfajri.dataprocessapp.domain.Product

class ProductListAdapter(private val products: List<Product>, private val onClick: (Product) -> Unit?): RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.apply {
                product.apply {
                    tvTitle.text = name
                    tvDesc.text = desc
                    tvPrice.text = price.toString()
                    tvStock.text = "Stok: $stock"
                }

                binding.root.setOnClickListener {
                    onClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ProductViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}