package com.example.popcornchallenge.core.data

import com.example.popcornchallenge.core.model.data.Movie
import kotlinx.coroutines.flow.Flow

interface MovieResourceRepository {
    fun getMovies(page: Int): Flow<Movie>
}
