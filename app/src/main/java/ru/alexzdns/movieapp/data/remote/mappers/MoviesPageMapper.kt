package ru.alexzdns.movieapp.data.remote.mappers

import javax.inject.Inject
import ru.alexzdns.movieapp.BuildConfig
import ru.alexzdns.movieapp.data.remote.models.ApiGenre
import ru.alexzdns.movieapp.data.remote.models.ApiMovie
import ru.alexzdns.movieapp.data.remote.models.ApiMovies
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.domain.models.MoviesPage


class MoviesPageMapper @Inject constructor(
    private val movieMapper: MovieMapper,
) {
    fun fromApiModel(apiMovies: ApiMovies, genres: Map<Int, String>) = MoviesPage(
        page = apiMovies.page,
        movies = apiMovies.movies.map { movieMapper.fromApiModel(it, genres) },
        totalPages = apiMovies.totalPages,
        totalResults = apiMovies.totalResults,
    )
}