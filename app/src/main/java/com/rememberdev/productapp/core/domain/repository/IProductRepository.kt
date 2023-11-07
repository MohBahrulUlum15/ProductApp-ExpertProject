package com.rememberdev.productapp.core.domain.repository

import com.rememberdev.productapp.core.data.Resource
import com.rememberdev.productapp.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getAllProduct(): Flow<Resource<List<Product>>>

    fun getFavoriteProduct(): Flow<List<Product>>

    fun setFavoriteProduct(product: Product, state: Boolean)
}