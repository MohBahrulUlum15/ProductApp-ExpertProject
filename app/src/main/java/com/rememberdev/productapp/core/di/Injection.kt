package com.rememberdev.productapp.core.di

import android.content.Context
import com.rememberdev.productapp.core.data.ProductRepository
import com.rememberdev.productapp.core.data.source.local.LocalDataSource
import com.rememberdev.productapp.core.data.source.local.room.ProductDatabase
import com.rememberdev.productapp.core.data.source.remote.RemoteDataSource
import com.rememberdev.productapp.core.data.source.remote.network.ApiConfig
import com.rememberdev.productapp.core.domain.repository.IProductRepository
import com.rememberdev.productapp.core.domain.usecase.ProductInteractor
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase
import com.rememberdev.productapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IProductRepository {
        val database = ProductDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.productDao())
        val appExecutors = AppExecutors()

        return ProductRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideProductUseCase(context: Context): ProductUseCase{
        val repository = provideRepository(context)
        return ProductInteractor(repository)
    }
}