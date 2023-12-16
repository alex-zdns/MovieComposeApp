package ru.alexzdns.movieapp.data.remote.mappers

import javax.inject.Inject
import ru.alexzdns.movieapp.BuildConfig
import ru.alexzdns.movieapp.data.remote.models.ApiGenre
import ru.alexzdns.movieapp.data.remote.models.ApiMovie
import ru.alexzdns.movieapp.data.remote.models.ApiMovieDetails
import ru.alexzdns.movieapp.domain.models.Movie

class MovieMapper @Inject constructor() {
    fun fromApiModel(apiModel: ApiMovie, genres: Map<Int, String>) = Movie(
        id = apiModel.id,
        title = apiModel.title,
        overview = apiModel.overview,
        poster = BuildConfig.IMAGE_BASE_URL + BuildConfig.POSTER_SIZES_PATCH + apiModel.posterPath,
        backdrop = BuildConfig.IMAGE_BASE_URL + BuildConfig.BACKDROP_SIZES_PATCH + apiModel.backdropPath,
        ratings = apiModel.voteAverage / 2.0f,
        numberOfRatings = apiModel.voteCount,
        genres = apiModel.genresIds.mapNotNull { genres[it] },
        isAdult = apiModel.adult
    )

    fun fromApiModel(apiModel: ApiMovieDetails) = Movie(
        id = apiModel.id,
        title = apiModel.title,
        overview = apiModel.overview,
        poster = BuildConfig.IMAGE_BASE_URL + BuildConfig.POSTER_SIZES_PATCH + apiModel.posterPath,
        backdrop = BuildConfig.IMAGE_BASE_URL + BuildConfig.BACKDROP_SIZES_PATCH + apiModel.backdropPath,
        ratings = apiModel.voteAverage / 2.0f,
        numberOfRatings = apiModel.voteCount,
        genres = apiModel.genres.map { it.name },
        isAdult = apiModel.adult
    )
}