package com.rememberdev.productapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase

class HomeViewModel(productUseCase: ProductUseCase) : ViewModel() {
    val product = productUseCase.getAllProduct().asLiveData()
}