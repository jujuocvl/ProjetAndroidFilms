package com.example.myfirstapplication.ui

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    //@GET("search/movie")
    //suspend fun getFilmParMotCle(@Query("motcle") motcle: String): TmbdResult
    @GET("trending/movie/week")
    suspend fun getFilmTendance(@Query("api_key") api_key: String): TmbdResult

}