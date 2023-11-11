package com.rememberdev.productapp.dynamicfavorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase

class DynamicFavoriteViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val product = productUseCase.getFavoriteProduct().asLiveData()
}