package com.example.popcornchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popcornchallenge.domain.repository.IMovieDiscoverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDiscoverViewModel(
    private val movieDiscoverRepository: IMovieDiscoverRepository
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieDiscoverRepository.getMovies(page = 1)
                .collect {

                }
        }
    }
}
