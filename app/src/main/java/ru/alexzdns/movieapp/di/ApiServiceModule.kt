package ru.alexzdns.movieapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.alexzdns.movieapp.BuildConfig
import ru.alexzdns.movieapp.data.remote.MovieApi
import ru.alexzdns.movieapp.data.remote.interceptors.APIKeyInterceptor

@Module
@InstallIn(SingletonComponent::class)
open class ApiServiceModule {
    companion object {
        const val JSON_MIME_TYPE = "application/json"
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(APIKeyInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @ExperimentalSerializationApi
    @Provides
    @Singleton
    open fun provideRetrofit(
        json: Json,
        client: OkHttpClient
    ): Retrofit {
        val contentType = JSON_MIME_TYPE.toMediaType()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}