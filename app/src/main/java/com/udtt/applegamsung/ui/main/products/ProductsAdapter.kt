package com.udtt.applegamsung.ui.main.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.databinding.ItemProductBinding

class ProductsAdapter : ListAdapter<DisplayedProduct, ProductsAdapter.ViewHolder>(DiffCallBack()) {

    private var itemClickListener: ProductClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val displayedProduct = getItem(position)

        holder.bind(displayedProduct)
    }

    fun setItemClickListener(listener: ProductClickListener) {
        itemClickListener = listener
    }

    private fun createItemClickListener(
        displayedProduct: DisplayedProduct
    ) = View.OnClickListener { view ->
        toggleItemIsSelected(view)
        itemClickListener?.onProductClick(displayedProduct, view.isSelected)
    }

    private fun toggleItemIsSelected(view: View) {
        view.isSelected = !view.isSelected
    }

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(displayedProduct: DisplayedProduct) {
            binding.displayedProduct = displayedProduct
            binding.itemClickListener = createItemClickListener(displayedProduct)
        }
    }

    private class DiffCallBack : DiffUtil.ItemCallback<DisplayedProduct>() {
        override fun areItemsTheSame(
            oldItem: DisplayedProduct,
            newItem: DisplayedProduct
        ): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(
            oldItem: DisplayedProduct,
            newItem: DisplayedProduct
        ): Boolean {
            return oldItem == newItem
        }
    }
}