package com.ariefrahmanfajri.dataprocessapp.presentation

import androidx.lifecycle.*
import com.ariefrahmanfajri.dataprocessapp.data.local.ProductModel
import com.ariefrahmanfajri.dataprocessapp.domain.Product
import com.ariefrahmanfajri.dataprocessapp.domain.ProductUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel(val useCase: ProductUseCase) : ViewModel() {
    private var _productLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    val productLiveData: LiveData<List<Product>> get() = _productLiveData

    fun getAllProduct() =
        useCase.getAllProduct().asLiveData()

    fun getSpecifiedProduct(productId: Int) =
        useCase.getSpecifiedProduct(productId).asLiveData()

    fun insertProduct(productModel: ProductModel) {
        CoroutineScope(Dispatchers.IO).launch {
            useCase.insertProduct(productModel)
        }
    }

    fun updateProduct(newStock: Int, productId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            useCase.updateProduct(newStock, productId)
        }
    }
}