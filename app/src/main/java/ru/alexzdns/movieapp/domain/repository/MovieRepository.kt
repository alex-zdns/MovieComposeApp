package ru.alexzdns.movieapp.domain.repository

import javax.inject.Inject
import ru.alexzdns.movieapp.data.remote.MovieApi
import ru.alexzdns.movieapp.data.remote.mappers.MovieMapper
import ru.alexzdns.movieapp.data.remote.mappers.MoviesPageMapper
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.domain.models.MoviesPage

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
    private val moviesPageMapper: MoviesPageMapper,
) {
    suspend fun loadMoviesFromServer(page: Int, genresMap: Map<Int, String>): MoviesPage {
        val moviesPage = movieApi.getMovieList(page = page)
        return moviesPageMapper.fromApiModel(moviesPage, genresMap)
    }

    suspend fun loadGenres(): Map<Int, String> {
        return movieApi.getGenres().genres.associateBy({ it.id }, { it.name })
    }

    suspend fun loadMovieDetails(movieId: Long): Movie {
        return movieApi.getMovieDetail(movieId).let(movieMapper::fromApiModel)
    }
}