package com.example.popcornchallenge.domain.model

data class Movie(
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val genreIds: List<Int>,
    val genres: List<String>
)
