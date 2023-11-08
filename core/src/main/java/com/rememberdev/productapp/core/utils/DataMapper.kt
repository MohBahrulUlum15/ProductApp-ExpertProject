package com.rememberdev.productapp.core.utils

import com.rememberdev.productapp.core.data.source.local.entity.ProductEntity
import com.rememberdev.productapp.core.data.source.remote.response.ProductResponse
import com.rememberdev.productapp.core.domain.model.Product

object DataMapper {
    fun mapResponseToEntities(input: List<ProductResponse>): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        input.map {
            val product = ProductEntity(
                productId = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                discountPercentage = it.discountPercentage,
                rating = it.rating,
                stock = it.stock,
                brand = it.brand,
                category = it.category,
                thumbnail = it.thumbnail,
                isFavorite = false
            )
            productList.add(product)
        }
        return productList
    }

    fun mapEntitiesToDomain(input: List<ProductEntity>): List<Product> =
        input.map {
            Product(
                productId = it.productId,
                title = it.title,
                description = it.description,
                price = it.price,
                discountPercentage = it.discountPercentage,
                rating = it.rating,
                stock = it.stock,
                brand = it.brand,
                category = it.category,
                thumbnail = it.thumbnail,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Product) = ProductEntity(
        productId = input.productId,
        title = input.title,
        description = input.description,
        price = input.price,
        discountPercentage = input.discountPercentage,
        rating = input.rating,
        stock = input.stock,
        brand = input.brand,
        category = input.category,
        thumbnail = input.thumbnail,
        isFavorite = input.isFavorite
    )
}