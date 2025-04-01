package com.example.popcornchallenge.domain.repository

import com.example.popcornchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDetailsRepository {
    fun getMovie(id: Int): Flow<Movie>
}
