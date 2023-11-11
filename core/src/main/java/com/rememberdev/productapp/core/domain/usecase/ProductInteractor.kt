package com.rememberdev.productapp.core.domain.usecase

import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.core.domain.repository.IProductRepository

class ProductInteractor(private val productRepository: IProductRepository) : ProductUseCase {
    override fun getAllProduct() = productRepository.getAllProduct()

    override fun searchProducts(query: String) = productRepository.searchProducts(query)

    override fun getFavoriteProduct() = productRepository.getFavoriteProduct()

    override fun setFavoriteProduct(product: Product, state: Boolean) =
        productRepository.setFavoriteProduct(product, state)
}