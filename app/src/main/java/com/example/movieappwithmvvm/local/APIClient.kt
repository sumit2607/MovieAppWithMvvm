package com.example.movieappwithmvvm.local

import com.example.movieappwithmvvm.local.response.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("movie/popular")
    suspend fun getResponseFromAPI(
        @Query("api_key") api_key : String,
        @Query("page") page:Int
    ) : ResponseModel

}