package com.ariefrahmanfajri.dataprocessapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val stock: Int,
    val desc: String,
    val price: Int
): Parcelable
