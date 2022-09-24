package com.ariefrahmanfajri.dataprocessapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productModel: ProductModel)

    @Query("Select * From products")
    fun getAllProduct(): Flow<List<ProductModel>>

    @Query("UPDATE products SET stock=:newStock WHERE id=:id")
    fun updateProduct(newStock: Int, id: Int)

    @Query("Select * From products WHERE id=:productId")
    fun getSpecifiedProduct(productId: Int): Flow<ProductModel>
}