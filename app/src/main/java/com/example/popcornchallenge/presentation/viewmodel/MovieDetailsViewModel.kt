package com.example.popcornchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _movieState: MutableStateFlow<MovieDetailState<Movie>> = MutableStateFlow(MovieDetailState())
    val movieState = _movieState.asStateFlow()

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase(id).collect { movie ->
                _movieState.update { state ->
                    state.copy(isLoading = false, data = movie)
                }
            }
        }
    }
}

data class MovieDetailState<T>(val isLoading: Boolean = false, val data: T? = null)