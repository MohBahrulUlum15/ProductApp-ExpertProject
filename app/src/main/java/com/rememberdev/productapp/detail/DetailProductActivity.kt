package com.rememberdev.productapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rememberdev.productapp.R
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.databinding.ActivityDetailProductBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProductActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private val detailProductViewModel: DetailProductViewModel by viewModel()
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailProduct = intent.getParcelableExtra<Product>(EXTRA_DATA)
        showDetail(detailProduct)
    }

    private fun showDetail(detailProduct: Product?) {
        detailProduct?.let {
            supportActionBar?.title = detailProduct.title
            binding.content.tvDetailDescription.text = detailProduct.description
            Glide.with(this@DetailProductActivity)
                .load(detailProduct.thumbnail)
                .into(binding.ivDetailImage)

            var statusFavorite = detailProduct.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener{
                statusFavorite = !statusFavorite
                detailProductViewModel.setFavoriteProduct(detailProduct, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}