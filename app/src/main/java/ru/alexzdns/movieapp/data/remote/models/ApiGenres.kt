package ru.alexzdns.movieapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGenres (
    @SerialName("genres")
    val genres: List<ApiGenre>
)