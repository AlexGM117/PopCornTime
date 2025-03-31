package com.example.popcornchallenge.domain.repository

import com.example.popcornchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDiscoverRepository {
    fun getMovies(page: Int): Flow<List<Movie>>
}
