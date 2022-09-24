package com.ariefrahmanfajri.apicallingapp.data.repository

import com.ariefrahmanfajri.apicallingapp.data.Api
import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.domain.IUserRepository
import com.ariefrahmanfajri.apicallingapp.domain.User
import com.ariefrahmanfajri.apicallingapp.utils.ApiResponse
import com.ariefrahmanfajri.apicallingapp.utils.ErrorResponse
import com.ariefrahmanfajri.apicallingapp.utils.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class UserRepository(private val web: Api): IUserRepository {
    override fun getAllUsers(): Flowable<Resource<List<User>>> {
        val mCompositeDisposable = CompositeDisposable()

        val result = PublishSubject.create<Resource<List<User>>>()
        result.onNext(Resource.Loading(null))

        val register = web.getAllUsers()
        val response = register
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete{
                mCompositeDisposable.dispose()
            }
            .subscribe {
                result.onNext(Resource.Loading(null))

                when(it) {
                    is ApiResponse.Success -> {
                        result.onNext(Resource.Success(it.data.map { it.toUser() }))
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Failure(it.errorMessage))
                    }
                }
            }
        mCompositeDisposable.add(response)
        return result.toFlowable(BackpressureStrategy.BUFFER)

    }

    override fun createUser(body: UserBody): Flowable<Resource<User>> {
        val mCompositeDisposable = CompositeDisposable()

        val result = PublishSubject.create<Resource<User>>()
        result.onNext(Resource.Loading(null))

        val register = web.createUser(body)
        val response = register
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete{
                mCompositeDisposable.dispose()
            }
            .subscribe {
                result.onNext(Resource.Loading(null))

                when(it) {
                    is ApiResponse.Success -> {
                        result.onNext(Resource.Success(it.data.toUser()))
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Failure(it.errorMessage))
                    }
                }
            }
        mCompositeDisposable.add(response)
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}