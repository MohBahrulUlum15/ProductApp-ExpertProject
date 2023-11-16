package com.rememberdev.productapp.di

import com.rememberdev.productapp.core.domain.usecase.ProductInteraction
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase
import com.rememberdev.productapp.presentation.detail.DetailProductViewModel
import com.rememberdev.productapp.presentation.home.HomeViewModel
import com.rememberdev.productapp.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase> { ProductInteraction(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}