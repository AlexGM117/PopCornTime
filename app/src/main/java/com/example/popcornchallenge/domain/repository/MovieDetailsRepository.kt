package com.example.popcornchallenge.domain.repository

import com.example.popcornchallenge.data.remote.IMDBRemoteDataSource
import com.example.popcornchallenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailsRepository(private val dataSource: IMDBRemoteDataSource): IMovieDetailsRepository {
    override fun getMovie(id: Int): Flow<Movie> = flow {
        val movie = dataSource.getMovie(id)
        emit(movie.mapToMovieDomain())
    }
}