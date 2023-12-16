package ru.alexzdns.movieapp.domain.models


class MoviesPage(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
)