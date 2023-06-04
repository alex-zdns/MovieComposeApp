package ru.alexzdns.movieapp.data.remote

import ru.alexzdns.movieapp.BuildConfig
import ru.alexzdns.movieapp.data.remote.models.ApiGenre
import ru.alexzdns.movieapp.data.remote.models.ApiMovie
import ru.alexzdns.movieapp.domain.models.Movie

class MoviesLoader(
    private val movieApi: MovieApi
) {
    suspend fun loadMoviesFromServer(): List<Movie> {
        val genresMap = movieApi.getGenres().genres
            .associateBy { it.id }

        val moviesDto = movieApi.getMovieList().movies

        return mapMovie(moviesDto, genresMap)
    }

    private fun mapMovie(moviesDto: List<ApiMovie>, genres: Map<Int, ApiGenre>): List<Movie> =
        moviesDto.map { apiModel ->
            Movie(
                id = apiModel.id,
                title = apiModel.title,
                overview = apiModel.overview,
                poster = BuildConfig.IMAGE_BASE_URL + BuildConfig.POSTER_SIZES_PATCH + apiModel.posterPath,
                backdrop = BuildConfig.IMAGE_BASE_URL + BuildConfig.BACKDROP_SIZES_PATCH + apiModel.backdropPath,
                ratings = apiModel.voteAverage / 2.0f,
                numberOfRatings = apiModel.voteCount,
                genres = apiModel.genresIds.mapNotNull { genres[it]?.name },
                isAdult = apiModel.adult
            )
        }
}