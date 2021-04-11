package com.mismaiti.chuckjokes.di.component

import android.app.Application
import com.mismaiti.chuckjokes.ui.MyApplication
import com.mismaiti.chuckjokes.di.module.BindingModule
import com.mismaiti.chuckjokes.di.module.DatabaseModule
import com.mismaiti.chuckjokes.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    BindingModule::class,
    DatabaseModule::class
])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: MyApplication?)
}