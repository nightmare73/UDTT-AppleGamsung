package com.udtt.applegamsung.ui.main.applecare

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.data.entity.DisplayedProduct
import com.udtt.applegamsung.databinding.FragmentCheckApplecareBinding
import com.udtt.applegamsung.ui.main.MainViewModel
import com.udtt.applegamsung.ui.main.adapter.MainViewPagerAdapter.Companion.FRAGMENT_PRODUCTS
import com.udtt.applegamsung.ui.main.products.ProductClickListener
import com.udtt.applegamsung.ui.main.products.ProductsAdapter
import com.udtt.applegamsung.ui.util.BaseFragment

/**
 * Created By Yun Hyeok
 * on 3월 24, 2020
 */

class AppleCareCheckFragment : BaseFragment(), ProductClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainViewModel = ViewModelProvider(activity!!, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val productsAdapter = createProductsAdapter()
        val binding = FragmentCheckApplecareBinding.inflate(inflater).apply {
            rvProducts.adapter = productsAdapter
            lifecycleOwner = this@AppleCareCheckFragment
        }

        initView(binding, mainViewModel)

        mainViewModel.selectedProducts.observe(this, Observer { products ->
            productsAdapter.submitList(products.map { it.toDisplayedProduct() })
        })

        return binding.root
    }

    override fun onProductClick(displayedProduct: DisplayedProduct) {
        mainViewModel.handleHasAppleCare(displayedProduct.product)
    }

    private fun initView(binding: FragmentCheckApplecareBinding, mainViewModel: MainViewModel) {
        binding.btnBack.setOnClickListener { mainViewModel.movePageTo(FRAGMENT_PRODUCTS) }
        initAdmob(binding.banner)
    }

    private fun createProductsAdapter(): ProductsAdapter {
        return ProductsAdapter().apply { setItemClickListener(this@AppleCareCheckFragment) }
    }

    companion object {
        private var INSTANCE: AppleCareCheckFragment? = null
        fun getInstance() = INSTANCE
            ?: AppleCareCheckFragment().apply { INSTANCE = this }
    }
}