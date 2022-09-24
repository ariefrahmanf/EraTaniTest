package com.ariefrahmanfajri.dataprocessapp.data.repository

import com.ariefrahmanfajri.dataprocessapp.data.local.LocalDataSource
import com.ariefrahmanfajri.dataprocessapp.data.local.ProductModel
import com.ariefrahmanfajri.dataprocessapp.domain.IProductRepository
import com.ariefrahmanfajri.dataprocessapp.domain.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(private val localDataSource: LocalDataSource) : IProductRepository {
    override fun getAllProduct(): Flow<List<Product>> {
        return localDataSource.getAllProduct().map {
            it.map { it.toProduct() }
        }
    }

    override fun getSpecifiedProduct(productId: Int): Flow<Product> {
        return localDataSource.getSpecifiedProduct(productId).map { it.toProduct() }
    }

    override suspend fun insertProduct(productModel: ProductModel) {
        localDataSource.insertProduct(productModel)
    }

    override suspend fun updateProduct(newStock: Int, productId: Int) {
        localDataSource.updateProduct(newStock, productId)
    }
}