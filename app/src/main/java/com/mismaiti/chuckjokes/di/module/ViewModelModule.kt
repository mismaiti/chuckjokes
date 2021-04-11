package com.mismaiti.chuckjokes.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mismaiti.chuckjokes.ui.ViewModelFactory
import com.mismaiti.chuckjokes.di.annotation.ViewModelKey
import com.mismaiti.chuckjokes.ui.category.CategoryViewModel
import com.mismaiti.chuckjokes.ui.detail.DetailViewModel
import com.mismaiti.chuckjokes.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ApiModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMovieDetailViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindContext(application: Application): Context
}