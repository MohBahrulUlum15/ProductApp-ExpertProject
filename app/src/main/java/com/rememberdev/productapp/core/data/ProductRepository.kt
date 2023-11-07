package com.rememberdev.productapp.core.data

import com.rememberdev.productapp.core.data.source.local.LocalDataSource
import com.rememberdev.productapp.core.data.source.remote.RemoteDataSource
import com.rememberdev.productapp.core.data.source.remote.network.ApiResponse
import com.rememberdev.productapp.core.data.source.remote.response.ProductResponse
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.core.domain.repository.IProductRepository
import com.rememberdev.productapp.core.utils.AppExecutors
import com.rememberdev.productapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IProductRepository {

    companion object {
        @Volatile
        private var instance: ProductRepository? = null
    }

    override fun getAllProduct(): Flow<Resource<List<Product>>> =
        object : NetworkBoundResource<List<Product>, List<ProductResponse>>() {
            override fun loadFromDB(): Flow<List<Product>> {
                return localDataSource.getAllProduct().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Product>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponse>>> =
                remoteDataSource.getAllProduct()

            override suspend fun saveCallResult(data: List<ProductResponse>) {
                val productList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertProduct(productList)
            }
        }.asFlow()

    override fun getFavoriteProduct(): Flow<List<Product>> {
        return localDataSource.getFavoriteProduct().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteProduct(product: Product, state: Boolean) {
        val productEntity = DataMapper.mapDomainToEntity(product)
        appExecutors.diskIO().execute { localDataSource.setFavoriteProduct(productEntity, state) }
    }
}