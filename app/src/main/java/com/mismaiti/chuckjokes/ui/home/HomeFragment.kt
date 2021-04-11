package com.mismaiti.chuckjokes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mismaiti.chuckjokes.adapter.CategoriesAdapter
import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.common.UiState
import com.mismaiti.chuckjokes.databinding.FragmentHomeBinding
import com.mismaiti.chuckjokes.ui.disableHomeAsUp
import com.mismaiti.chuckjokes.ui.base.BaseFragment

class HomeFragment : BaseFragment() {

    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter(context) }
    private val fragmentBinding by viewBinding(FragmentHomeBinding::inflate)
    private val homeViewModel by viewModel { it.get(HomeViewModel::class.java) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        homeViewModel.getCategories()
        with(fragmentBinding) {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            categoryListRv.layoutManager = layoutManager
            categoryListRv.adapter = adapter
        }

        setupObserver()

        setupActionBar()
        return fragmentBinding.root
    }

    private fun setupActionBar() {
        with(mainActivity) {
            searchQueryListener = { it ->
                val action = it?.let { query ->
                    HomeFragmentDirections.actionNavigationHomeToNavigationDetail(
                        query
                    )
                }
                val navController = Navigation.findNavController(requireView())
                action?.let { action ->
                    navController.navigate(action)
                }
            }

            supportActionBar?.apply {
                show()
                title = "Categories"
                disableHomeAsUp()
            }
            searchMenuItem?.isVisible = true
        }
    }

    private fun setupObserver() {
        homeViewModel.action.observe(viewLifecycleOwner, Observer {
            when(it) {
                is UiState.Success -> {
                    fragmentBinding.itemLoading.root.visibility = View.GONE
                    adapter.loadCategoryList(it.data as List<JokesEntity>)
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