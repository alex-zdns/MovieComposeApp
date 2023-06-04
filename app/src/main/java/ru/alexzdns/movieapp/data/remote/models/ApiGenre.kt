package ru.alexzdns.movieapp.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ApiGenre (
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)