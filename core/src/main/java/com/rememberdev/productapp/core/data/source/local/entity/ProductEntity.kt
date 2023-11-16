package com.rememberdev.productapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "productId")
    var productId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "price")
    var price: Int,

    @ColumnInfo(name = "discountPercentage")
    var discountPercentage: Double,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "stock")
    var stock: Int,

    @ColumnInfo(name = "brand")
    var brand: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable