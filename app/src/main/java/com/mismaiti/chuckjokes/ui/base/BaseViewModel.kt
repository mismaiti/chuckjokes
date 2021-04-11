package com.mismaiti.chuckjokes.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mismaiti.chuckjokes.common.UiState
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    open val action = MutableLiveData<UiState>()

    open var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}