package com.ariefrahmanfajri.dataprocessapp

import android.app.Application
import com.ariefrahmanfajri.dataprocessapp.di.productDb
import com.ariefrahmanfajri.dataprocessapp.di.repoModule
import com.ariefrahmanfajri.dataprocessapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    productDb,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }
}