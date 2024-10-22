package com.example.myfirstapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun Films(mainViewModel: MainViewModel) {
    // Collecte l'état des films depuis le ViewModel
    val movies by mainViewModel.movies.collectAsState()

    //charger liste des films si liste vide
    LaunchedEffect(key1 = Unit) { //évite les boucles infinies
        mainViewModel.getFilmsInitiaux()
    }

    // Afficher la liste des films ou un message si la liste est vide
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(16.dp)
        ) {
            items(movies.size) { index ->
                val movie = movies[index]
                Column () {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "Description de l'image",
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }

