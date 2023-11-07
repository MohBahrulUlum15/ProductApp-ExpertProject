package com.rememberdev.productapp.di

import com.rememberdev.productapp.core.domain.usecase.ProductInteractor
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase
import com.rememberdev.productapp.detail.DetailProductViewModel
import com.rememberdev.productapp.favorite.FavoriteViewModel
import com.rememberdev.productapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase> { ProductInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
}