package ru.alexzdns.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.lang.Exception
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.create
import ru.alexzdns.movieapp.data.remote.MoviesLoader
import ru.alexzdns.movieapp.data.remote.NetworkModule
import ru.alexzdns.movieapp.domain.models.LoadableResult
import ru.alexzdns.movieapp.domain.models.Movie

class MoviesListViewModel : ViewModel() {
    private val moviesLoader = MoviesLoader(NetworkModule.retrofit.create())

    private val _uiState: MutableStateFlow<LoadableResult<List<Movie>>> =
        MutableStateFlow(LoadableResult.Loading)
    val uiState: StateFlow<LoadableResult<List<Movie>>> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = LoadableResult.Loading

            try {
                val movies = moviesLoader.loadMoviesFromServer()
                _uiState.value = LoadableResult.Success(movies)
            } catch (e: Exception) {
                Log.e(this::class.simpleName, e.toString())
                _uiState.value = LoadableResult.Failure(e)
            }
        }
    }
}