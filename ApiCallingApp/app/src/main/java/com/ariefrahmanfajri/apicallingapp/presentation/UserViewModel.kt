package com.ariefrahmanfajri.apicallingapp.presentation

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.domain.UserUseCase

class UserViewModel(private val useCase: UserUseCase): ViewModel() {
    fun getAllUser() = LiveDataReactiveStreams.fromPublisher(useCase.getAllUsers())
    fun createUser(body: UserBody) = LiveDataReactiveStreams.fromPublisher(useCase.createUser(body))
}