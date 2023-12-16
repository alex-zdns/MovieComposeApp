package ru.alexzdns.movieapp.ui.movie.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ru.alexzdns.movieapp.R
import ru.alexzdns.movieapp.ui.components.ErrorComponents
import ru.alexzdns.movieapp.ui.components.LoaderComponents
import ru.alexzdns.movieapp.ui.components.PagingErrorComponent
import ru.alexzdns.movieapp.ui.components.PagingLoadingComponent

@Composable
fun MovieListScreen(
    viewModel: MoviesListViewModel = hiltViewModel(),
    onMovieClick: (Long) -> Unit,
) {
    val lazyPagingItems = remember(viewModel) {
        viewModel.movieFlow
    }.collectAsLazyPagingItems()


    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Error -> ErrorComponents(lazyPagingItems::retry)
        LoadState.Loading -> LoaderComponents()
        is LoadState.NotLoading -> {
            val spanCount = integerResource(id = R.integer.movies_list_span)
            LazyVerticalGrid(
                columns = GridCells.Fixed(spanCount),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),

                // content padding
                contentPadding = PaddingValues(
                    all = 20.dp,
                ),
                content = {
                    items(lazyPagingItems.itemCount) { index ->

                        val movie = lazyPagingItems[index]

                        movie?.let {
                            MovieListItem(
                                movie = it,
                                onMovieClick = onMovieClick,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    item(span = { GridItemSpan(spanCount) }) {
                        HandleFooter(lazyPagingItems.loadState.append, lazyPagingItems::retry)
                    }
                }
            )
        }
    }
}

@Composable
fun HandleFooter(state: LoadState, retry: () -> Unit) {
    when (state) {
        is LoadState.Error -> PagingErrorComponent(refreshAction = retry)
        LoadState.Loading -> PagingLoadingComponent()
        is LoadState.NotLoading -> Unit
    }
}
