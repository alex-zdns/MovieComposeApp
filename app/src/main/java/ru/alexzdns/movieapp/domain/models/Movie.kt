package ru.alexzdns.movieapp.domain.models

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val genres: List<String>,
    val isAdult: Boolean,
)