package com.rememberdev.productapp.dynamicfavorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rememberdev.productapp.core.ui.ProductAdapter
import com.rememberdev.productapp.dynamicfavorite.databinding.ActivityDynamicFavoriteBinding
import com.rememberdev.productapp.dynamicfavorite.di.dynamicFavoriteModule
import com.rememberdev.productapp.dynamicfavorite.presentation.DynamicFavoriteViewModel
import com.rememberdev.productapp.presentation.detail.DetailProductActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DynamicFavoriteActivity : AppCompatActivity() {

    private val dynamicFavoriteViewModel: DynamicFavoriteViewModel by viewModel()

    private lateinit var binding: ActivityDynamicFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(dynamicFavoriteModule)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.favorite_product)

        getProductData()
    }

    private fun getProductData() {
        val productAdapter = ProductAdapter()
        productAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@DynamicFavoriteActivity, DetailProductActivity::class.java)
            intent.putExtra(DetailProductActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        dynamicFavoriteViewModel.product.observe(this, { dataProduct ->
            productAdapter.setData(dataProduct)
            binding.viewEmpty.root.visibility =
                if (dataProduct.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvProduct) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }
}