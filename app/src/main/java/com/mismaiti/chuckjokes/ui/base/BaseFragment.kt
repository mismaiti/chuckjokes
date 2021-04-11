package com.mismaiti.chuckjokes.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.mismaiti.chuckjokes.ui.MainActivity
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.reflect.KClass

open class BaseFragment : DaggerFragment() {

    lateinit var mainActivity: MainActivity
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

    }

    inline fun <T: ViewBinding> Fragment.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }

    inline fun<T: ViewModel> viewModel(
        crossinline viewModelProvider: (ViewModelProvider) -> T) =
        lazy(LazyThreadSafetyMode.NONE) {
            viewModelProvider.invoke(ViewModelProvider(this, viewModelFactory))
        }
}

fun TextView.setTime(stringDate: String?) {
    val simpleDateFormat = SimpleDateFormat("MMMM dd, yyyy ")
    val date = Date.parse(stringDate)
    val stringDate = simpleDateFormat.format(date)
    text = "Updated at:" + stringDate
}
