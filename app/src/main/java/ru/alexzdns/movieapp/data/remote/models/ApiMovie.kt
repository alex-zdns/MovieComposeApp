package ru.alexzdns.movieapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovie (
    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("genre_ids")
    val genresIds: List<Int>,

    @SerialName("id")
    val id: Long,

    @SerialName("overview")
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("title")
    val title: String,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("adult")
    val adult: Boolean,
)