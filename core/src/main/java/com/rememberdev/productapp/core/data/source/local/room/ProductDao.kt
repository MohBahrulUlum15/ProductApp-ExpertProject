package com.rememberdev.productapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rememberdev.productapp.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE isFavorite = 1")
    fun getFavoriteProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE title LIKE '%' ||:like || '%'")
    fun searchProducts(like : String) : Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Update
    fun updateFavoriteProduct(product: ProductEntity)
}