package com.mismaiti.chuckjokes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mismaiti.chuckjokes.adapter.CategoriesAdapter
import com.mismaiti.chuckjokes.common.UiState
import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.data.JokesResponse
import com.mismaiti.chuckjokes.databinding.FragmentDetailBinding
import com.mismaiti.chuckjokes.ui.enableHomeAsUp
import com.mismaiti.chuckjokes.ui.base.BaseFragment
import com.mismaiti.chuckjokes.ui.base.setTime

class DetailFragment : BaseFragment() {

    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(context) }
    private val fragmentBinding by viewBinding(FragmentDetailBinding::inflate)
    private val detailViewModel by viewModel { it.get(DetailViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(fragmentBinding) {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            categoryListRv.layoutManager = layoutManager
            categoryListRv.adapter = adapter
        }

        arguments?.getString("query")?.let { detailViewModel.getJokesFromQuery(it) }
        setupObserver()

        setupActionBar()
        return fragmentBinding.root
    }

    private fun setupActionBar() {
        with(mainActivity) {
            supportActionBar?.apply {
                show()
                enableHomeAsUp()
            }
            searchMenuItem?.isVisible = false
        }
    }

    private fun setupObserver() {
        detailViewModel.action.observe(viewLifecycleOwner, Observer {
            when(it) {
                is UiState.Success -> {

                    fragmentBinding.itemLoading.root.visibility = View.GONE
                    val jokesList = (it.data as JokesResponse).result
                    adapter.loadCategoryList(jokesList)
                }
                is UiState.Failure -> {

                }
                else -> {
                    fragmentBinding.itemLoading.root.visibility = View.VISIBLE
                }

            }
        })
    }
}