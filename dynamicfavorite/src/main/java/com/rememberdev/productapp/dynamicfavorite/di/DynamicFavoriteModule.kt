package com.rememberdev.productapp.dynamicfavorite.di

import com.rememberdev.productapp.dynamicfavorite.presentation.DynamicFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dynamicFavoriteModule = module {
    viewModel { DynamicFavoriteViewModel(get()) }
}