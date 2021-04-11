package com.mismaiti.chuckjokes.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mismaiti.chuckjokes.ui.MainActivity
import com.mismaiti.chuckjokes.common.UiState
import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.databinding.FragmentCategoryBinding
import com.mismaiti.chuckjokes.ui.enableHomeAsUp
import com.mismaiti.chuckjokes.ui.base.BaseFragment
import com.mismaiti.chuckjokes.ui.base.setTime
import java.text.SimpleDateFormat
import java.util.*

class CategoryFragment : BaseFragment() {

    private val categoryViewModel by viewModel() { it.get(CategoryViewModel::class.java) }
    private val fragmentBinding by viewBinding(FragmentCategoryBinding::inflate)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        setupObserver()
        arguments?.getString("category")?.let {
            categoryViewModel.getCategories(it)
            setupActionbar(it)
        }
        return fragmentBinding.root
    }

    private fun setupActionbar(it: String) {
        with(mainActivity) {
            supportActionBar?.apply {
                show()
                enableHomeAsUp()
                title = it.capitalize()
            }
            searchMenuItem?.isVisible = false
        }
    }

    private fun setupObserver () {
        categoryViewModel.action.observe(viewLifecycleOwner, Observer {
            when(it) {
                is UiState.Success -> {
                    with(fragmentBinding) {
                        val jokesEntity = it.data as JokesEntity
                        Glide.with(requireActivity()).load(jokesEntity.iconUrl).into(categoryIconIv)
                        categoryNameTv.text = jokesEntity.category
                        categoryValueTv.text = jokesEntity.value
                        categoryUpdatedAtTv.setTime(jokesEntity.updatedAt)
                    }
                }
                is UiState.Failure -> {

                }
                else -> {

                }
            }
        })
    }
}