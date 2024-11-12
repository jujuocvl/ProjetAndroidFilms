package com.example.myfirstapplication.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    //Initialisation de Retrofit pour lien avec API
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(TmdbAPI::class.java)
    val api_key = "317519a83cc36ab9367ba50e5aa75b40"

    //FILMS : StateFlow maitient l'état de la liste des films
    val listMovies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = listMovies

    //SERIES: StateFlow maitient l'état de la liste des series
    val listSeries = MutableStateFlow<List<Series>>(emptyList())
    val series: StateFlow<List<Series>> = listSeries

    //ACTEURS: StateFlow maitient l'état de la liste des series
    val listActors = MutableStateFlow<List<Actors>>(emptyList())
    val actors: StateFlow<List<Actors>> = listActors

    var searchText by mutableStateOf("")

    fun getFilms() {
        viewModelScope.launch {
            // Appel à l'API pour obtenir la liste des films
            val filmsList = api.getFilmTendance(api_key)
            listMovies.value = filmsList.results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            // Appel à l'API pour obtenir la liste des séries
            val seriesList = api.getSeriesTendance(api_key)
            listSeries.value = seriesList.results
        }
    }

    fun getActors() {
        viewModelScope.launch {
            // Appel à l'API pour obtenir la liste des acteurs
            val actorsList = api.getActeursTendance(api_key)
            listActors.value = actorsList.results
        }
    }

    fun SearchMovie() { //recherhe de films
        viewModelScope.launch {
            try {
                listMovies.value = api.getFilmParMotCle(api_key, searchText).results
            } catch (e: Exception) {
                Log.e("MainViewModel", "searchMovie: $e") // enregistre l'erreur
            }
        }
    }

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            val movieResult = api.MovieDetails(id, api_key,"fr","credits")
            val movie = movieResult
            listMovies.value = listOf(movie)
        }
    }

    fun SearchSerie() { //recherche les series
        viewModelScope.launch {
            try {
                listSeries.value = api.getSerieParMotCle(api_key, searchText).results
            } catch (e: Exception) {
                Log.e("MainViewModel", "searchMovie: $e") // enregistre l'erreur
            }
        }
    }

    fun getSerieDetails(id: String) {
        viewModelScope.launch {
            val serieResult = api.SeriesDetails(id, api_key, "fr", "credits")
            val serie = serieResult
            listSeries.value = listOf(serie)
        }
    }

    fun SearchActor() { //recherche les acteurs
        viewModelScope.launch {
            try {
                listActors.value = api.getActeurParMotCle(api_key, searchText).results
            } catch (e: Exception) {
                Log.e("MainViewModel", "searchMovie: $e") // enregistre l'erreur
            }
        }
    }

    /*fun getActorsInMovie(movieId: String) {
        viewModelScope.launch {
            try {
                val actorResult = api.getActeurFilmographie(movieId, api_key)
                val actors = actorResult.cast ?: emptyList()
                listActors.value = actors
                Log.d("aaa", "getActorsInMovie: actors list size = ${actors.size}")
            } catch (e: Exception) {
                Log.e("aaaa", "getActorsInMovie: $e")
            }
        }
    }*/
}