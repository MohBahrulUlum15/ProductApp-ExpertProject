package com.rememberdev.productapp.presentation.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rememberdev.productapp.core.ui.ProductAdapter
import com.rememberdev.productapp.databinding.FragmentSearchBinding
import com.rememberdev.productapp.presentation.detail.DetailProductActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val productAdapter = ProductAdapter()
            productAdapter.onItemClick = { select ->
                val intent = Intent(activity, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.EXTRA_DATA, select)
                startActivity(intent)
            }

            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    searchViewModel.searchProducts(newText.toString())
                        .observe(viewLifecycleOwner) { result ->
                            if (result.isNotEmpty()) {
                                binding.tvErrorMessage.visibility = View.GONE
                                binding.rvProduct.visibility = View.VISIBLE
                                productAdapter.setData(result)
                            } else {
                                binding.tvErrorMessage.visibility = View.VISIBLE
                                binding.rvProduct.visibility = View.GONE
                            }
                        }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
            })

//            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    searchViewModel.searchProducts(query.toString())
//                        .observe(viewLifecycleOwner) { result ->
//                            if (result.isNotEmpty()) {
//                                binding.tvErrorMessage.visibility = View.GONE
//                                binding.rvProduct.visibility = View.VISIBLE
//                                productAdapter.setData(result)
//                            } else {
//                                binding.tvErrorMessage.visibility = View.VISIBLE
//                                binding.rvProduct.visibility = View.GONE
//                            }
//                        }
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    return false
//                }
//            })
//
//            homeViewModel.product.observe(viewLifecycleOwner, {product ->
//                if (product != null){
//                    when(product){
//                        is com.rememberdev.productapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
//                        is com.rememberdev.productapp.core.data.Resource.Success -> {
//                            binding.progressBar.visibility = View.GONE
//                            productAdapter.setData(product.data)
//                        }
//                        is com.rememberdev.productapp.core.data.Resource.Error -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text = product.message ?: getString(R.string.something_wrong)
//                        }
//                    }
//                }
//            })

            with(binding.rvProduct) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = productAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}