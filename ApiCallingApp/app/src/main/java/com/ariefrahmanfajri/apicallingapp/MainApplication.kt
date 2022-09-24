package com.ariefrahmanfajri.apicallingapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.ariefrahmanfajri.apicallingapp.di.networkModule
import com.ariefrahmanfajri.apicallingapp.di.repositoryModule
import com.ariefrahmanfajri.apicallingapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}