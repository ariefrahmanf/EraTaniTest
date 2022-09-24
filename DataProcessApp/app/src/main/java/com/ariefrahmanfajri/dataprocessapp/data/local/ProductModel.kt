package com.ariefrahmanfajri.dataprocessapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ariefrahmanfajri.dataprocessapp.domain.Product

@Entity(tableName = "products")
data class ProductModel(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "stock")
    val stock: Int,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "price")
    val price: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toProduct() = Product(
        id, name, stock, desc, price
    )
}
