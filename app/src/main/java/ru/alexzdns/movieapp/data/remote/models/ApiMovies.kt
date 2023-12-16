package ru.alexzdns.movieapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovies(
    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val movies: List<ApiMovie>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int,
)