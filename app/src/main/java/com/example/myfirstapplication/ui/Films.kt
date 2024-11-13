package com.example.myfirstapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowWidthSizeClass
import coil.compose.AsyncImage
import com.example.myfirstapplication.FilmDetailDest

@Composable
fun FilmsScreen(mainViewModel: MainViewModel, navController: NavController) {
    // Collecte l'état des films depuis le ViewModel
    val movies by mainViewModel.movies.collectAsState()

    //charger liste des films si liste vide
    LaunchedEffect(key1 = Unit) { //évite les boucles infinies
        mainViewModel.getFilms()
    }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp) //16
            ) {
                items(movies.size) { index -> //itérable
                    val movie = movies[index]
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { // Navigate to movieDetails.kt
                                navController.navigate(FilmDetailDest(movie.id))
                            },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 12.dp,
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "Description de l'image",
                                alignment = Alignment.Center,
                            )
                            Text(
                                text = movie.title,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                            Text(
                                //date
                                text = movie.release_date,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                lineHeight = 1.sp,
                            )
                        }
                    }
                }
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(10.dp) //16
            ) {
                items(movies.size) { index -> //itérable
                    val movie = movies[index]
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { // Navigate to movieDetails.kt
                                navController.navigate(FilmDetailDest(movie.id))
                            },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 12.dp,
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "Description de l'image",
                                alignment = Alignment.Center,
                            )
                            Text(
                                text = movie.title,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                            Text(
                                //date
                                text = movie.release_date,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                            )
                        }
                    }
                }
            }
        }
    }
}


