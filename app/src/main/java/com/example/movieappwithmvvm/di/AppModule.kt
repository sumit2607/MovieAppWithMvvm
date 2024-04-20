package com.example.movieappwithmvvm.di

import com.example.movieappwithmvvm.extra.Constants.BASE_URL
import com.example.movieappwithmvvm.extra.Constants.TIMEOUT_TIME
import com.example.movieappwithmvvm.local.APIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApiService(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                    ).apply { HttpLoggingInterceptor.Level.BODY }
                    .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }

}