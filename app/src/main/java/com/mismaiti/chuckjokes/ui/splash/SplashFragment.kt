package com.mismaiti.chuckjokes.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mismaiti.chuckjokes.databinding.FragmentSplashBinding
import com.mismaiti.chuckjokes.ui.base.BaseFragment
import kotlin.concurrent.fixedRateTimer

class SplashFragment : BaseFragment() {

    private val fragmentBinding by viewBinding(FragmentSplashBinding::inflate)
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainActivity.supportActionBar?.hide()
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fixedRateTimer("timer", false, 0, 2000) {
            this@SplashFragment.run {
                this@SplashFragment.view?.let {
                    val action = SplashFragmentDirections.actionNavigationSplashToNavigationHome()
                    navController = Navigation.findNavController(requireView())
                    navController.navigate(action)
                }
            }
        }
    }

}