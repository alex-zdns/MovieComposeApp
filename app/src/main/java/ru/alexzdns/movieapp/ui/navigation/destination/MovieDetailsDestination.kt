package ru.alexzdns.movieapp.ui.navigation.destination

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.alexzdns.movieapp.ui.movie.details.MovieDetailsScreen

private const val BASE_ROUTE = "movieDetails"
const val ID_KEY = "id"

fun NavGraphBuilder.movieDetails() {
    composable(
        route = "$BASE_ROUTE/{$ID_KEY}",
        arguments = listOf(
            navArgument(ID_KEY) {
                type = NavType.LongType
                nullable = false
            }
        )
    ) {
        MovieDetailsScreen()
    }
}

fun NavController.navigateToMovieDetails(movieId: Long) {
    navigate("$BASE_ROUTE/$movieId")
}