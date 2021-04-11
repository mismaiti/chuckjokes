package com.mismaiti.chuckjokes.common

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: Any): UiState()
    data class Failure(val throwable: Throwable): UiState()
}

