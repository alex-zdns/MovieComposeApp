package ru.alexzdns.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.alexzdns.movieapp.ui.movie.list.MovieListScreen
import ru.alexzdns.movieapp.ui.navigation.destination.MAIN_SCREEN_ROUTE
import ru.alexzdns.movieapp.ui.navigation.destination.main
import ru.alexzdns.movieapp.ui.navigation.destination.movieDetails
import ru.alexzdns.movieapp.ui.navigation.destination.navigateToMovieDetails

@Composable
fun MovieAppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE,
    ) {
        main(
            onMovieClick = navController::navigateToMovieDetails
        )

        movieDetails()
    }
}