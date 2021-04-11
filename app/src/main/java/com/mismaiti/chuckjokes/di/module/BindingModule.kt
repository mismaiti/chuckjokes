package com.mismaiti.chuckjokes.di.module

import com.mismaiti.chuckjokes.ui.MainActivity
import com.mismaiti.chuckjokes.ui.base.BaseFragment
import com.mismaiti.chuckjokes.ui.category.CategoryFragment
import com.mismaiti.chuckjokes.ui.detail.DetailFragment
import com.mismaiti.chuckjokes.ui.home.HomeFragment
import com.mismaiti.chuckjokes.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun provideBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideCategoriesFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun provideDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun provideSplashFragment(): SplashFragment


}