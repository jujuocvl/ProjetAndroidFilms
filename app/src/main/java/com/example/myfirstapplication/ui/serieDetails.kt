package com.example.myfirstapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun serieDetails(mainViewModel: MainViewModel, serieId: String, navController: NavController) {
    val listSeries by mainViewModel.listSeries.collectAsStateWithLifecycle()
    val serie = listSeries.find { it.id.toString() == serieId }

    //recupère la liste des acteurs dans le film
    val cast by mainViewModel.listActors.collectAsStateWithLifecycle()

    //évite les if suivants le format de l'écran
    val configuration =
        LocalConfiguration.current //recupère la configuration de l'écran avec les dimensions de l'écran et l'orientation
    val format =
        configuration.screenWidthDp < configuration.screenHeightDp //si l'écran est en mode portrait ou paysage
    val genre = serie?.genres?.joinToString(", ") { it.name }
    val columns =
        if (format) 2 else 4 //si l'écran est en mode portrait on affiche 1 colonne sinon 2 colonnes

    mainViewModel.getSerieDetails(serieId)

    //verifie sur serie est nul
    serie?.let { //si serie n'est pas null, alors continue execution

        LaunchedEffect(key1 = Unit) { //évite les boucles infinies
            mainViewModel.getSerieDetails(serieId)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            item(span = { GridItemSpan(columns) }) {
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${serie.poster_path}",
                        contentDescription = "affiche serie",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = serie.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date de sortie: ${serie.first_air_date}",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Genres : $genre",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = serie.overview,
                        textAlign = TextAlign.Justify,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Distribution :",
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            items(cast) { cast ->
                Card(
                    modifier = Modifier
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w342${cast.profile_path}",
                            contentDescription = "Photo acteur",
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = cast.original_name ?: "Inconnu",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = cast.character ?: "Inconnu",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
