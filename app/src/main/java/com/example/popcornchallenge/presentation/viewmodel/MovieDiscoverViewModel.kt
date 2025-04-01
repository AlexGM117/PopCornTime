package com.example.popcornchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.usecase.GetMovieDiscoverUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MovieDiscoverViewModel(
    private val movieDiscoverUseCase: GetMovieDiscoverUseCase
) : ViewModel() {
    private val _movieState: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty())
    val movieState: MutableStateFlow<PagingData<Movie>> get() = _movieState
    init {
        onEvent(DiscoverEvent.GetMovies)
    }

    fun onEvent(event: DiscoverEvent) {
        when (event) {
            is DiscoverEvent.GetMovies -> {
                viewModelScope.launch {
                    discoverMovies()
                }
            }

            is DiscoverEvent.NavigateDetail -> TODO()
        }
    }

    private suspend fun discoverMovies() {
        movieDiscoverUseCase
            .invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _movieState.value = it
            }
    }
}

sealed class DiscoverEvent {
    object GetMovies : DiscoverEvent()
    data class NavigateDetail(val id: Int): DiscoverEvent()
}