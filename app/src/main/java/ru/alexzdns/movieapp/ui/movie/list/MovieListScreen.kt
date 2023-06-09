package ru.alexzdns.movieapp.ui.movie.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.alexzdns.movieapp.domain.models.LoadableResult
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.ui.components.ErrorComponents
import ru.alexzdns.movieapp.ui.components.LoaderComponents

@Composable
fun MovieListScreen(
    viewModel: MoviesListViewModel = viewModel(),
    onMovieClick: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is LoadableResult.Failure -> ErrorComponents(viewModel::loadData)
        LoadableResult.Loading -> LoaderComponents()
        is LoadableResult.Success -> {
            val movies = (uiState as LoadableResult.Success<List<Movie>>).value

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),


                // content padding
                contentPadding = PaddingValues(
                    all = 20.dp,
                ),
                content = {
                    items(movies.size) { index ->
                        MovieListItem(
                            movie = movies[index],
                            onMovieClick = onMovieClick,
                            modifier = Modifier.fillMaxWidth())
                    }
                }
            )
        }
    }
}