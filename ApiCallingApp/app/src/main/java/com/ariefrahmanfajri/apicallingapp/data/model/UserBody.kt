package com.ariefrahmanfajri.apicallingapp.data.model

import com.google.gson.annotations.SerializedName

data class UserBody(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: String
)
