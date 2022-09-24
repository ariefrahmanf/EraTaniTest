package com.ariefrahmanfajri.apicallingapp.utils

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("field")
    val field: String,
    @SerializedName("message")
    val message: String
)
