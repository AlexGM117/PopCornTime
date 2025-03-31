package com.example.popcornchallenge.core.data

import com.example.popcornchallenge.core.model.data.Movie
import com.example.popcornchallenge.core.network.MDBDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDiscoverRepository(
    private val network: MDBDataSource
) : MovieResourceRepository {
    override fun getMovies(page: Int): Flow<Movie>  = flow {
        val response = network.getMovieList(page)
        val movie = response.results.first()
        emit(Movie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            popularity = movie.popularity,
        ))
    }
}
