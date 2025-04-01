package com.example.popcornchallenge.data.remote.model

import com.example.popcornchallenge.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMovie(
    @SerialName("page") val page: Int?,
    @SerialName("results") val results: List<MovieResponse>?,
    @SerialName("total_pages") val totalPages: Int?,
    @SerialName("total_results") val totalResults: Int?
)

@Serializable
data class MovieResponse(
    @SerialName("adult") val adult: Boolean?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("genre_ids") val genreIds: List<Int?>? = emptyList(),
    @SerialName("id") val id: Int?,
    @SerialName("original_language") val originalLanguage: String?,
    @SerialName("original_title") val originalTitle: String?,
    @SerialName("overview") val overview: String?,
    @SerialName("popularity") val popularity: Double?,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("title") val title: String?,
    @SerialName("video") val video: Boolean?,
    @SerialName("vote_average") val voteAverage: Double?,
    @SerialName("vote_count") val voteCount: Int?,
    @SerialName("genres") val genres: List<Genre?>? = emptyList()
) {
    fun mapToMovieDomain(): Movie =
        Movie(
            id = id ?: 0,
            title = title ?: "",
            overview = overview ?: "",
            posterPath = posterPath ?: "",
            releaseDate = releaseDate ?: "",
            originalLanguage = originalLanguage ?: "",
            originalTitle = originalTitle ?: "",
            genreIds = genreIds?.filterNotNull() ?: emptyList(),
            genres = genres?.filterNotNull()?.map { it.name.orEmpty() } ?: emptyList()
        )
}

@Serializable
data class Genre(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?
)
