package com.rememberdev.productapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rememberdev.productapp.core.data.source.remote.network.ApiResponse
import com.rememberdev.productapp.core.data.source.remote.network.ApiService
import com.rememberdev.productapp.core.data.source.remote.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllProduct(): Flow<ApiResponse<List<ProductResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<ProductResponse>>>()

        //get data from remote api
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
}