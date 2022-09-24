package com.ariefrahmanfajri.apicallingapp.domain

import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.utils.Resource
import io.reactivex.Flowable

class UserInteractor(private val repo: IUserRepository): UserUseCase {
    override fun getAllUsers(): Flowable<Resource<List<User>>> {
        return repo.getAllUsers()
    }

    override fun createUser(body: UserBody): Flowable<Resource<User>> {
        return repo.createUser(body)
    }
}