package com.example.myfirstapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
fun movieDetails(mainViewModel: MainViewModel, movieId: String, navController: NavController) {
    mainViewModel.getMovieDetails(movieId)
    mainViewModel.getActorsInMovie(movieId)
    val listMovies by mainViewModel.listMovies.collectAsStateWithLifecycle()
    val movie = listMovies.find { it.id.toString() == movieId }

    //recupère la liste des acteurs dans le film
    val cast by mainViewModel.listActors.collectAsStateWithLifecycle()
    //évite les if suivants le format de l'écran
    val configuration =
        LocalConfiguration.current //recupère la configuration de l'écran avec les dimensions de l'écran et l'orientation
    val format =
        configuration.screenWidthDp < configuration.screenHeightDp //si l'écran est en mode portrait ou paysage
    val columns =
        if (format) 1 else 2 //si l'écran est en mode portrait on affiche 1 colonne sinon 2 colonnes
    val genre = movie?.genres?.joinToString(", ") { it.name }
    //verifie sur movie est nul
    movie?.let { film -> //si movie n'est pas null, alors continue execution

        LaunchedEffect(key1 = Unit) { //évite les boucles infinies
            mainViewModel.getMovieDetails(movieId)
            mainViewModel.getActorsInMovie(movieId)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            item {
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                        contentDescription = "affiche film",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date de sortie: ${movie.release_date}",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Genres : $genre",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.overview,
                        textAlign = TextAlign.Justify,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Distribution :",
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
//la LazyVerticalGrid doit créer un item pour chaque élément de la liste soit par acteurs dans la distribution
            //film.credits?.let { credits ->
            items(cast) { casting ->
                Row {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w342${casting.profile_path}",
                        contentDescription = "Photo acteur",
                        modifier = Modifier.size(50.dp)
                    )
                    Column {
                        Text(text = casting.name ?: "Inconnu", fontWeight = FontWeight.Bold)
                        Text(text = casting.character ?: "Inconnu")
                    }
                }
            }
        }
    }
}


