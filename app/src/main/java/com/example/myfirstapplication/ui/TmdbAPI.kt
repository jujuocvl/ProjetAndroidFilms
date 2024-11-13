package com.example.myfirstapplication.ui

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {

    @GET("trending/movie/week")
    suspend fun getFilmTendance(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbMoviesResult

    @GET("trending/tv/week")
    suspend fun getSeriesTendance(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbSeriesResult

    @GET("trending/person/week")
    suspend fun getActeursTendance(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbActorsResult

    @GET("search/movie")
    suspend fun getFilmParMotCle(
        @Query("api_key") api_key: String,
        @Query("query") searchText: String,
        @Query("language") language: String = "fr"
    ): TmdbMoviesResult

    @GET("movie/{id}")
    suspend fun MovieDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String = "fr",
        @Query("append_to_response") appendToResponse: String = "credits"
    ): Movie

    @GET("tv/{id}")
    suspend fun SeriesDetails(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") langague: String = "fr",
        @Query("append_to_response") appendToResponse: String = "credits"
    ): Series

    @GET("search/tv")
    suspend fun getSerieParMotCle(
        @Query("api_key") api_key: String,
        @Query("query") searchText: String,
        @Query("language") language: String = "fr"
    ): TmdbSeriesResult

    @GET("search/person")
    suspend fun getActeurParMotCle(
        @Query("api_key") api_key: String,
        @Query("query") searchText: String,
        @Query("language") language: String = "fr"
    ): TmdbActorsResult

    @GET("search/collection")
    suspend fun getCollectionHorreur(
        @Query("api_key") api_key: String,
        @Query("query") searchText: String = "horror",
    ): CollectionResults

    /*@GET("person/{id}/movie_credits")
    suspend fun getActeurFilmographie(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbMoviesResult
     */

}
