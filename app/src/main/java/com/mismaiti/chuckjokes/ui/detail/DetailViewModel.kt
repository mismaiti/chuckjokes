package com.mismaiti.chuckjokes.ui.detail

import com.mismaiti.chuckjokes.api.JokesService
import com.mismaiti.chuckjokes.common.UiState
import com.mismaiti.chuckjokes.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val jokesService: JokesService): BaseViewModel() {

    fun getJokesFromQuery(query: String) {
        action.value = UiState.Loading
        compositeDisposable.addAll(jokesService.getJokesByKeyword(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                action.value = UiState.Success(it)
            },{
                action.value = UiState.Failure(it)
            }))
    }
}