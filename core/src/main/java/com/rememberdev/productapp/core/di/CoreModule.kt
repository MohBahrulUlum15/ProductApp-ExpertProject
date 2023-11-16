package com.rememberdev.productapp.core.di

import androidx.room.Room
import com.rememberdev.productapp.core.data.ProductRepository
import com.rememberdev.productapp.core.data.source.local.LocalDataSource
import com.rememberdev.productapp.core.data.source.local.room.ProductDatabase
import com.rememberdev.productapp.core.data.source.remote.RemoteDataSource
import com.rememberdev.productapp.core.data.source.remote.network.ApiService
import com.rememberdev.productapp.core.domain.repository.IProductRepository
import com.rememberdev.productapp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ProductDatabase>().productDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rememberdev".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ProductDatabase::class.java,
            "Product.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IProductRepository> {
        ProductRepository(
            get(),
            get(),
            get()
        )
    }
}