package ru.alexzdns.movieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.alexzdns.movieapp.data.remote.models.ApiGenres
import ru.alexzdns.movieapp.data.remote.models.ApiMovieDetails
import ru.alexzdns.movieapp.data.remote.models.ApiMovies

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenres(): ApiGenres

    @GET("movie/{path}")
    suspend fun getMovieList(@Path("path") path: String = "now_playing"): ApiMovies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Long): ApiMovieDetails
}