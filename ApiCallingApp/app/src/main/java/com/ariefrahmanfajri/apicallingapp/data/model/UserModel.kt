package com.ariefrahmanfajri.apicallingapp.data.model

import com.ariefrahmanfajri.apicallingapp.domain.User
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: String
) {
    fun toUser() = User(
        id, name, email, gender, status
    )
}
