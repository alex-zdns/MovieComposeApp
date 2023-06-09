package ru.alexzdns.movieapp.domain.repository

import javax.inject.Inject
import ru.alexzdns.movieapp.data.remote.MovieApi
import ru.alexzdns.movieapp.data.remote.mappers.MovieMapper
import ru.alexzdns.movieapp.domain.models.Movie

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper,
) {
    suspend fun loadMoviesFromServer(): List<Movie> {
        val genresMap = movieApi.getGenres().genres.associateBy { it.id }

        val moviesDto = movieApi.getMovieList().movies

        return moviesDto.map { movieMapper.fromApiModel(it, genresMap) }
    }

    suspend fun loadMovieDetails(movieId: Long): Movie {
        return movieApi.getMovieDetail(movieId).let(movieMapper::fromApiModel)
    }
}