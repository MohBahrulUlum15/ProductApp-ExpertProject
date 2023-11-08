package com.rememberdev.productapp.core.data.source.remote.network

import com.rememberdev.productapp.core.data.source.remote.response.ListProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getList(): ListProductResponse
}