package com.rememberdev.productapp.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rememberdev.productapp.R
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.databinding.ActivityDetailProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailProductViewModel: DetailProductViewModel by viewModel()
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        @Suppress("DEPRECATION")
        val detailProduct = intent.getParcelableExtra<Product>(EXTRA_DATA)
        showDetail(detailProduct)
    }

    private fun showDetail(detailProduct: Product?) {
        detailProduct?.let {
            supportActionBar?.title = detailProduct.title
            Glide.with(this@DetailProductActivity)
                .load(detailProduct.thumbnail)
                .into(binding.imageView)

            binding.content.tvDetailDescription.text = detailProduct.description
            binding.content.tvPrice.text = "${detailProduct.price.toString()} $"
            binding.content.tvDiscount.text = "${detailProduct.discountPercentage.toString()} %"
            binding.content.tvRating.text = detailProduct.rating.toString()
            binding.content.tvStock.text = detailProduct.stock.toString()
            binding.content.tvBrand.text = detailProduct.brand
            binding.content.tvCategory.text = detailProduct.category

            var statusFavorite = detailProduct.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailProductViewModel.setFavoriteProduct(detailProduct, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}