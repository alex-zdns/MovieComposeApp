package ru.alexzdns.movieapp.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import ru.alexzdns.movieapp.data.paging.MoviePagingSource
import ru.alexzdns.movieapp.domain.repository.MovieRepository

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    val movieFlow = Pager(PagingConfig(20)) {
        MoviePagingSource(repository)
    }.flow
}