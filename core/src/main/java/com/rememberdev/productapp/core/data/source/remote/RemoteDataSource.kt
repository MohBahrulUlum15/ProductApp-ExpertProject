package com.rememberdev.productapp.core.data.source.remote

import android.util.Log
import com.rememberdev.productapp.core.data.source.remote.network.ApiResponse
import com.rememberdev.productapp.core.data.source.remote.network.ApiService
import com.rememberdev.productapp.core.data.source.remote.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getAllProduct(): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.products
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.products))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
    }
}