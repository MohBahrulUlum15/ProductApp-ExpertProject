package com.rememberdev.productapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rememberdev.productapp.R
import com.rememberdev.productapp.core.domain.model.Product
import com.rememberdev.productapp.databinding.ItemListProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {

    private var listData = ArrayList<Product>()
    var onItemClick: ((Product) -> Unit)? = null

    fun setData(newListData: List<Product>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListProductBinding.bind(itemView)
        fun bind(data: Product) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.brand
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_product, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}