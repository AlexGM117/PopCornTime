package com.example.popcornchallenge.presentation.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcornchallenge.data.remote.IMDBRemoteDataSource
import com.example.popcornchallenge.domain.model.Movie
import kotlinx.io.IOException

class MovieDiscoverPagingSource(
    private val remoteDataSource: IMDBRemoteDataSource
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = remoteDataSource.getMovieList(currentPage)
                .results?.map {
                    it.mapToMovieDomain()
                } ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
