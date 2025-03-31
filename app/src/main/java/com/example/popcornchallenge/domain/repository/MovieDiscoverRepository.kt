package com.example.popcornchallenge.domain.repository

import com.example.popcornchallenge.domain.model.Movie
import com.example.popcornchallenge.data.remote.IMDBRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDiscoverRepository(
    private val network: IMDBRemoteDataSource
) : IMovieDiscoverRepository {
    override fun getMovies(page: Int): Flow<List<Movie>>  = flow {
        val movieList = network.getMovieList(page)
            .results.map {
                Movie(
                    id = it.id,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    popularity = it.popularity,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle
                )
            }

        emit(movieList)
    }
}
