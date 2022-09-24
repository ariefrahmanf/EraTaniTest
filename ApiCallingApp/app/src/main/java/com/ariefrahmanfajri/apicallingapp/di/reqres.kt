package com.ariefrahmanfajri.apicallingapp.di

import com.ariefrahmanfajri.apicallingapp.data.Api
import com.ariefrahmanfajri.apicallingapp.data.ApiClient
import com.ariefrahmanfajri.apicallingapp.data.repository.UserRepository
import com.ariefrahmanfajri.apicallingapp.domain.IUserRepository
import com.ariefrahmanfajri.apicallingapp.domain.UserInteractor
import com.ariefrahmanfajri.apicallingapp.domain.UserUseCase
import com.ariefrahmanfajri.apicallingapp.presentation.UserViewModel
import com.ariefrahmanfajri.apicallingapp.utils.MainInterceptor
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://gorest.co.in/public/v2/"

val networkModule = module {
    single { MainInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .addInterceptor(get<MainInterceptor>())
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiClient::class.java)
    }
}

val repositoryModule = module {
    single { Api(get()) }
    single <IUserRepository>{ UserRepository(get()) }
    single <UserUseCase>{ UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}