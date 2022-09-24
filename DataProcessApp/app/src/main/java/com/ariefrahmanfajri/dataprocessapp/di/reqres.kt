package com.ariefrahmanfajri.dataprocessapp.di

import androidx.room.Room
import com.ariefrahmanfajri.dataprocessapp.data.local.LocalDataSource
import com.ariefrahmanfajri.dataprocessapp.data.local.ProductDatabase
import com.ariefrahmanfajri.dataprocessapp.data.repository.ProductRepository
import com.ariefrahmanfajri.dataprocessapp.domain.IProductRepository
import com.ariefrahmanfajri.dataprocessapp.domain.ProductInteractor
import com.ariefrahmanfajri.dataprocessapp.domain.ProductUseCase
import com.ariefrahmanfajri.dataprocessapp.presentation.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDb = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ProductDatabase::class.java,
            "product_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<ProductDatabase>().productDao() }
    single { LocalDataSource(get()) }
}

val repoModule = module {
    single <IProductRepository>{ ProductRepository(get()) }
    single <ProductUseCase>{ ProductInteractor(get()) }
}

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
}