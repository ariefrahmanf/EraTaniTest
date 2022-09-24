package com.ariefrahmanfajri.apicallingapp.data

import android.annotation.SuppressLint
import com.ariefrahmanfajri.apicallingapp.data.model.UserBody
import com.ariefrahmanfajri.apicallingapp.data.model.UserModel
import com.ariefrahmanfajri.apicallingapp.utils.ApiResponse
import com.ariefrahmanfajri.apicallingapp.utils.ErrorResponse
import com.google.gson.Gson
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class Api(private val web: ApiClient) {

    @SuppressLint("CheckResult")
    fun getAllUsers(): Flowable<ApiResponse<List<UserModel>>> {
        val resultData = PublishSubject.create<ApiResponse<List<UserModel>>>()

        val client = web.getAllUsers()

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(if (response.isNotEmpty()) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun createUser(body: UserBody): Flowable<ApiResponse<UserModel>> {
        val result = PublishSubject.create<ApiResponse<UserModel>>()

        val post = web.createUser(body)
        post.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.onNext(ApiResponse.Success(it))
            }, { e ->
                if (e is HttpException) {
                    val error = e as HttpException
                    val errorBody = error.response()?.errorBody()?.string()

                    val gson = Gson()
                    val errorResponse = gson.fromJson<List<ErrorResponse>>(
                        errorBody,
                        ErrorResponse::class.java
                    ) as ArrayList<ErrorResponse>

                    result.onNext(ApiResponse.Error(errorResponse[0].message))
                } else {
                    result.onNext(ApiResponse.Error(e.message as String))

                }
            })

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

}