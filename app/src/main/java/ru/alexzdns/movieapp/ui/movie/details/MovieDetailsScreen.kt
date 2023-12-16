package ru.alexzdns.movieapp.ui.movie.details

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import ru.alexzdns.movieapp.domain.models.LoadableResult
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.ui.components.ErrorComponents
import ru.alexzdns.movieapp.ui.components.LoaderComponents

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is LoadableResult.Failure -> ErrorComponents(viewModel::loadData)
        LoadableResult.Loading -> LoaderComponents()
        is LoadableResult.Success -> {
            val movie = (uiState as LoadableResult.Success<Movie>).value
            MovieDetailsView(movie = movie)
        }
    }
}