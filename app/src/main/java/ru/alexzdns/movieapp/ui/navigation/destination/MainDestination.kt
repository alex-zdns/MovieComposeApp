package ru.alexzdns.movieapp.ui.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.alexzdns.movieapp.ui.movie.list.MovieListScreen

const val MAIN_SCREEN_ROUTE = "main"

fun NavGraphBuilder.main(onMovieClick: (Long) -> Unit) {
    composable(MAIN_SCREEN_ROUTE) {
        MovieListScreen(
            onMovieClick = onMovieClick
        )
    }
}