package com.ariefrahmanfajri.dataprocessapp.domain

import com.ariefrahmanfajri.dataprocessapp.data.local.ProductModel
import kotlinx.coroutines.flow.Flow

class ProductInteractor(val repo: IProductRepository): ProductUseCase {
    override fun getAllProduct(): Flow<List<Product>> {
        return repo.getAllProduct()
    }

    override fun getSpecifiedProduct(productId: Int): Flow<Product> {
        return repo.getSpecifiedProduct(productId)
    }

    override suspend fun insertProduct(productModel: ProductModel) {
        repo.insertProduct(productModel)
    }

    override suspend fun updateProduct(newStock: Int, productId: Int) {
        repo.updateProduct(newStock, productId)
    }

}