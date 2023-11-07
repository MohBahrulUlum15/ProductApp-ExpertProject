package com.rememberdev.productapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListProductResponse (
    @field:SerializedName("products")
    val products: List<ProductResponse>
)