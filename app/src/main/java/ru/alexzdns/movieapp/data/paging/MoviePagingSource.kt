package ru.alexzdns.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.alexzdns.movieapp.domain.models.Movie
import ru.alexzdns.movieapp.domain.repository.MovieRepository

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, Movie>() {

    private var genres: Map<Int, String>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            loadPage(params)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun loadPage(params: LoadParams<Int>): LoadResult.Page<Int, Movie> {
        val pageNumber = params.key ?: 1
        val genres = getGenres()
        val page = repository.loadMoviesFromServer(pageNumber, genres)


        val previousKey = if (pageNumber == 1) null else pageNumber - 1
        val nextKey = if (pageNumber >= page.totalPages) null else pageNumber + 1

        return LoadResult.Page(
            data = page.movies,
            prevKey = previousKey,
            nextKey = nextKey,
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    private suspend fun getGenres(): Map<Int, String> {
        return genres ?: repository.loadGenres().apply {
            genres = this
        }
    }
}