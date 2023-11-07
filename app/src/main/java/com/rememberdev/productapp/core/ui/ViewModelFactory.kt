package com.rememberdev.productapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rememberdev.productapp.core.di.Injection
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase
import com.rememberdev.productapp.detail.DetailProductViewModel
import com.rememberdev.productapp.favorite.FavoriteViewModel
import com.rememberdev.productapp.home.HomeViewModel

class ViewModelFactory private constructor(private val producttUseCase: ProductUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideProductUseCase(context)
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(producttUseCase) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(producttUseCase) as T
            }

            modelClass.isAssignableFrom(DetailProductViewModel::class.java) -> {
                DetailProductViewModel(producttUseCase) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}