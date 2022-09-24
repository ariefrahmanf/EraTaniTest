package com.ariefrahmanfajri.apicallingapp.domain

import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.utils.Resource
import io.reactivex.Flowable

interface UserUseCase {
    fun getAllUsers(): Flowable<Resource<List<User>>>
    fun createUser(body: UserBody): Flowable<Resource<User>>
}