package ru.alexzdns.movieapp.domain.models

sealed interface LoadableResult<out R> {
    object Loading : LoadableResult<Nothing>
    class Success<R>(val value: R) : LoadableResult<R>
    class Failure(val throwable: Throwable) : LoadableResult<Nothing>
}