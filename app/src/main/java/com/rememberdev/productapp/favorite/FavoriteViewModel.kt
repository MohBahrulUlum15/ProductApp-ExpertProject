package com.rememberdev.productapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase

class FavoriteViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val favoriteProduct = productUseCase.getFavoriteProduct().asLiveData()
}