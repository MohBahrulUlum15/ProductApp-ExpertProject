package com.rememberdev.productapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.core.domain.usecase.ProductUseCase

class SearchViewModel(private val productUseCase: ProductUseCase) : ViewModel() {
    fun searchProducts(query : String) : LiveData<List<Product>> {
        return productUseCase.searchProducts(query).asLiveData()
    }
}