package com.rememberdev.productapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase

class DetailProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    fun setFavoriteProduct(product: Product, newStatus: Boolean) =
        productUseCase.setFavoriteProduct(product, newStatus)
}