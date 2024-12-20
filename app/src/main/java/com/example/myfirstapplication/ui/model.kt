package com.example.myfirstapplication.ui

data class TmdbMoviesResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
    val cast: List<Movie> = listOf()//liste des acteurs du film
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val genres: List<Genre> = listOf(),
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String = "",
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val credits: Credits = Credits()
)

data class TmdbSeriesResult(
    val page: Int,
    val results: List<Series>,
    val total_pages: Int,
    val total_results: Int
)

data class Series(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val genres: List<Genre> = listOf(),
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
    val credits: Credits = Credits()
)

data class TmdbActorsResult(
    val page: Int,
    val results: List<Actors>,
    val total_pages: Int,
    val total_results: Int
)

data class Actors(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val order: Int,
)

data class Genre(
    val id: Int,
    val name: String
)

data class Credits( //liste de l'équipe du film
    val cast: List<Cast> = listOf()
)

data class Cast( //acteurs du film
    val id: String,
    val name: String,
    val profile_path: String,
    val character: String,
    val original_name: String,
)
data class CollectionResults(
    val results: List<Collection>,
)

data class Collection (
    val adult :Boolean,
    val results: List<Collection>,
    val backdrop_path : String,
    val id: String,
    val name:String,
    val original_language : String,
    val original_name: String,
    val overview : String,
    val poster_path: String,
)