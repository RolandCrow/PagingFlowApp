package com.example.compose_lifecycle_components_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class RepositoriesViewModel(
    private val reposPagingSource: RepositoriesPagingSource = RepositoriesPagingSource()
): ViewModel()  {
    val repositories: Flow<PagingData<Repository>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {
            reposPagingSource
        }).flow.cachedIn(viewModelScope)
    val timerState = mutableStateOf("")
    var timer: CustomCountdown = CustomCountdown(
        onTick = { msLeft ->
            timerState.value =
                (msLeft/1000).toString() + " seconds left"
        },
        onFinish = {
            timerState.value = "You won the prize!"
        }
    )

    override fun onCleared() {
        super.onCleared()
        timer.stop()
    }
}