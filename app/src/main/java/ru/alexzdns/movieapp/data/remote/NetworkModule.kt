package ru.alexzdns.movieapp.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import ru.alexzdns.movieapp.BuildConfig
import ru.alexzdns.movieapp.data.remote.interceptors.APIKeyInterceptor


object NetworkModule {
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(APIKeyInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    val retrofit: Retrofit by lazy {
        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}