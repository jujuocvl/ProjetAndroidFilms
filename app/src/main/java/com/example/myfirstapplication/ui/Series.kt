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
import com.example.myfirstapplication.SerieDetailDest

@Composable
fun SeriesScreen(mainViewModel: MainViewModel, navController: NavController) {
    // Collecte l'état des series depuis le ViewModel
    val series by mainViewModel.series.collectAsState()

    //charger liste des films si liste vide
    LaunchedEffect(key1 = Unit) { //évite les boucles infinies
        mainViewModel.getSeries()
    }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(5.dp) //16
            ) {
                items(series.size) { index -> //itérable
                    val serie = series[index]
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                navController.navigate(SerieDetailDest(serie.id))
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
                                    model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                    contentDescription = "Description de l'image",
                                    alignment = Alignment.Center,
                                )
                                Text(
                                    text = serie.name,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                                Text(
                                    //date
                                    text = serie.first_air_date,
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
                    items(series.size) { index -> //itérable
                        val serie = series[index]
                        Card(
                            modifier = Modifier.padding(10.dp),
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
                                    model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                                    contentDescription = "Description de l'image",
                                    alignment = Alignment.Center,
                                )
                                Text(
                                    text = serie.name,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                                Text(
                                    //date
                                    text = serie.first_air_date,
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

        }
    }
