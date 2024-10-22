package com.example.myfirstapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import java.util.Date

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
            Column() {
                Card() {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                        modifier = Modifier.fillMaxWidth(0.95f), //limite la largeur de l'image
                        contentDescription = "Description de l'image",
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                    )
                    Text(
                        //date
                        text = movie.release_date,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        lineHeight = 1.sp,
                    )
                }
            }
        }
    }
}

