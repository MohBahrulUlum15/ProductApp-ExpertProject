package com.rememberdev.productapp.core.utils

import android.content.Context
import com.rememberdev.productapp.R
import com.rememberdev.productapp.core.data.source.remote.response.ProductResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.product).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<ProductResponse> {
        val list = ArrayList<ProductResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("products")

        for (i in 0 until listArray.length()) {
            val course = listArray.getJSONObject(i)

            val id = course.getString("id")
            val title = course.getString("title")
            val description = course.getString("description")
            val price = course.getInt("price")
            val discountPercentage = course.getDouble("discountPercentage")
            val rating = course.getDouble("rating")
            val stock = course.getInt("stock")
            val brand = course.getString("brand")
            val category = course.getString("category")
            val thumbnail = course.getString("thumbnail")

            val courseResponse = ProductResponse(
                id = id,
                title = title,
                description = description,
                price = price,
                discountPercentage = discountPercentage,
                rating = rating,
                stock = stock,
                brand = brand,
                category = category,
                thumbnail = thumbnail
            )

            list.add(courseResponse)
        }
        return list
    }
}