package com.ariefrahmanfajri.apicallingapp.data

import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.data.model.UserModel
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("users")
    fun getAllUsers(): Flowable<List<UserModel>>

    @POST("users")
    fun createUser(@Body bodyRequest: UserBody): Flowable<UserModel>
}