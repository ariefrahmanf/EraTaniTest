package com.ariefrahmanfajri.dataprocessapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}