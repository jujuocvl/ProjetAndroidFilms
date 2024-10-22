package com.example.myfirstapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class MainViewModel : ViewModel() {
    //StateFlow maitient l'état de la liste des films
    private val listMovies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = listMovies

    //Initialisation de Retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(TmdbAPI::class.java)
    val api_key= "317519a83cc36ab9367ba50e5aa75b40"

    // Fonction pour récupérer les films initiaux
    fun getFilmsInitiaux() {
        viewModelScope.launch {
            // Appel à l'API pour obtenir la liste des films
            val filmsList = api.getFilmTendance(api_key)
            listMovies.value = filmsList.results
        }
    }
}