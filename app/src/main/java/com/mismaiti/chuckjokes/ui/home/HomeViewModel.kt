package com.mismaiti.chuckjokes.ui.home

import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.api.JokesService
import com.mismaiti.chuckjokes.common.UiState
import com.mismaiti.chuckjokes.database.JokesDao
import com.mismaiti.chuckjokes.repo.JokesRepository
import com.mismaiti.chuckjokes.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    jokesService: JokesService, jokesDao: JokesDao) : BaseViewModel() {

    private val jokesRepository = JokesRepository(jokesDao, jokesService)

    fun getCategories() {
        action.value = UiState.Loading
        compositeDisposable.addAll(
            jokesRepository.loadJokes().subscribe({
                action.value = UiState.Success(it)
            }, {
                action.value = UiState.Failure(it)
            })
        )

    }

}