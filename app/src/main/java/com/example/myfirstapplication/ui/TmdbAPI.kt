package com.example.myfirstapplication.ui

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun getFilmTendance(@Query("api_key") api_key: String, @Query("language") language:String="fr"): TmdbMoviesResult

    @GET("trending/serie/week")
    suspend fun getSeriesTendance(@Query("api_key") api_key: String, @Query("language") language:String="fr"): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun getActeursTendance(@Query("api_key") api_key: String, @Query("language") language:String="fr"): TmdbActorsResult

    @GET("movie/api_key")
    suspend fun getFilmParMotCle(@Query("api_key") api_key: String, @Query("language") language:String="fr"): TmdbMoviesResult


}