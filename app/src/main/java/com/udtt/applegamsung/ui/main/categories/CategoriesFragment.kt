package com.udtt.applegamsung.ui.main.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udtt.applegamsung.databinding.FragmentCategoriesBinding
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment() {

    private val viewModelFactory: ViewModelProvider.Factory by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoriesBinding.inflate(layoutInflater)

        val categoriesViewModel =
            ViewModelProvider(this, viewModelFactory)[CategoriesViewModel::class.java]

        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.setCategoryClickListener {

        }
        binding.categories.adapter = categoriesAdapter

        subscribeCategories(categoriesViewModel, categoriesAdapter)

        return binding.root
    }

    private fun subscribeCategories(viewModel: CategoriesViewModel, adapter: CategoriesAdapter) {
        viewModel.categories.observe(this, Observer {
            adapter.initCategories(it)
        })
    }

    companion object {
        private var INSTANCE: CategoriesFragment? = null
        fun getInstance() = INSTANCE
            ?: CategoriesFragment().apply { INSTANCE = this }
    }
}