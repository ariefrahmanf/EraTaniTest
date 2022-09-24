package com.ariefrahmanfajri.dataprocessapp.data.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(val productDao: ProductDao) {
    suspend fun insertProduct(product: ProductModel) {
        productDao.insertProduct(product)
    }

     fun getAllProduct(): Flow<List<ProductModel>> {
        return productDao.getAllProduct()
    }

    fun getSpecifiedProduct(productId: Int): Flow<ProductModel> {
        return productDao.getSpecifiedProduct(productId)
    }

    suspend fun updateProduct(newStock: Int, productId: Int) {
        productDao.updateProduct(newStock, productId)
    }
}