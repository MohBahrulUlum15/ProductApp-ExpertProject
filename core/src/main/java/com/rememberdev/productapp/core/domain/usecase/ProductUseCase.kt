package com.rememberdev.productapp.core.domain.usecase

import com.rememberdev.productapp.core.data.Resource
import com.rememberdev.productapp.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    fun getAllProduct(): Flow<com.rememberdev.productapp.core.data.Resource<List<Product>>>

    fun getFavoriteProduct(): Flow<List<Product>>

    fun setFavoriteProduct(product: Product, state: Boolean)
}