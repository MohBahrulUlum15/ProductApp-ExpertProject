package com.rememberdev.productapp

import android.app.Application
import com.rememberdev.productapp.core.di.databaseModule
import com.rememberdev.productapp.core.di.networkModule
import com.rememberdev.productapp.core.di.repositoryModule
import com.rememberdev.productapp.di.useCaseModule
import com.rememberdev.productapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}