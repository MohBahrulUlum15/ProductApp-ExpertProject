package com.rememberdev.productapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("discountPercentage")
    val discountPercentage: Double,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("stock")
    val stock: Int,

    @field:SerializedName("brand")
    val brand: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("thumbnail")
    val thumbnail: String
)