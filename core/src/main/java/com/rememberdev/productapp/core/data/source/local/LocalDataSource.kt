package com.rememberdev.productapp.core.data.source.local

import com.rememberdev.productapp.core.data.source.local.entity.ProductEntity
import com.rememberdev.productapp.core.data.source.local.room.ProductDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val productDao: ProductDao){
    companion object{
        private var instance: com.rememberdev.productapp.core.data.source.local.LocalDataSource? = null
    }

    fun getAllProduct(): Flow<List<ProductEntity>> = productDao.getAllProduct()

    fun getFavoriteProduct(): Flow<List<ProductEntity>> = productDao.getFavoriteProduct()

    fun searchProducts(query : String) : Flow<List<ProductEntity>> = productDao.searchProducts(query)

    suspend fun insertProduct(productList: List<ProductEntity>) = productDao.insertProduct(productList)

    fun setFavoriteProduct(product: ProductEntity, newState: Boolean){
        product.isFavorite = newState
        productDao.updateFavoriteProduct(product)
    }
}