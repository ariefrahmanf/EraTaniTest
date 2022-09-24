package com.ariefrahmanfajri.dataprocessapp.domain

import com.ariefrahmanfajri.dataprocessapp.data.local.ProductModel
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getAllProduct(): Flow<List<Product>>
    fun getSpecifiedProduct(productId: Int): Flow<Product>
    suspend fun insertProduct(productModel: ProductModel)
    suspend fun updateProduct(newStock: Int, productId: Int)
}