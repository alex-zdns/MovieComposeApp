package ru.alexzdns.movieapp.ui.movie.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexzdns.movieapp.domain.models.LoadableResult
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.domain.repository.MovieRepository
import ru.alexzdns.movieapp.ui.navigation.destination.ID_KEY

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository,
) : ViewModel() {

    private val movieId: Long = savedStateHandle.get<Long>(ID_KEY) ?: error("movieId must be not null")

    private val _uiState: MutableStateFlow<LoadableResult<Movie>> =
        MutableStateFlow(LoadableResult.Loading)
    val uiState: StateFlow<LoadableResult<Movie>> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = LoadableResult.Loading

            try {
                val movies = repository.loadMovieDetails(movieId)
                _uiState.value = LoadableResult.Success(movies)
            } catch (e: Exception) {
                Log.e(this::class.simpleName, e.toString())
                _uiState.value = LoadableResult.Failure(e)
            }
        }
    }
}