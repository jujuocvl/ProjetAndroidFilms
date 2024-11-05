package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myfirstapplication.ui.ActeursScreen
import com.example.myfirstapplication.ui.Bouton
import com.example.myfirstapplication.ui.FilmsScreen
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme
import kotlinx.serialization.Serializable

import com.example.myfirstapplication.ui.MainViewModel
import com.example.myfirstapplication.ui.Presentation
import com.example.myfirstapplication.ui.Screen
import com.example.myfirstapplication.ui.SearchScreen
import com.example.myfirstapplication.ui.SeriesScreen
import com.example.myfirstapplication.ui.Socials
import com.example.myfirstapplication.ui.movieDetails

@Serializable
class FilmsDest

@Serializable
class ProfilDest

@Serializable
class SeriesDest

@Serializable
class ActeursDest

@Serializable
class SearchDest


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val viewmodel: MainViewModel by viewModels()
            val isProfilDest = currentDestination?.hasRoute<ProfilDest>() == true

            MyFirstApplicationTheme {
                Scaffold(
                    bottomBar = {
                        if (!isProfilDest) {
                            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) { // PORTRAIT
                                Surface(
                                    color = Color.Cyan
                                ) {
                                    NavigationBar(
                                        containerColor = Color.Cyan,
                                    ) {
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_local_movies_24),
                                                    contentDescription = "movies",
                                                )
                                            }, label = { Text("Films") },
                                            selected = currentDestination?.hasRoute<FilmsDest>() == true,
                                            onClick = { navController.navigate(FilmsDest()) }
                                        )
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_tv_24),
                                                    contentDescription = "series",
                                                )
                                            }, label = { Text("Séries") },
                                            selected = currentDestination?.hasRoute<SeriesDest>() == true,
                                            onClick = { navController.navigate(SeriesDest()) }
                                        )
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_person_24),
                                                    contentDescription = "acteurs",
                                                )
                                            }, label = { Text("Acteurs") },
                                            selected = currentDestination?.hasRoute<ActeursDest>() == true,
                                            onClick = { navController.navigate(ActeursDest()) })
                                    }
                                }
                            } else { //Bottom bar paysage
                                Surface(
                                    color = Color.Cyan
                                ) {
                                    NavigationRail(
                                        containerColor = Color.Cyan,
                                    ) {
                                        NavigationRailItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_local_movies_24),
                                                    contentDescription = "movies",
                                                )
                                            }, label = { Text("Films") },
                                            selected = currentDestination?.hasRoute<FilmsDest>() == true,
                                            onClick = { navController.navigate(FilmsDest()) }
                                        )
                                        NavigationRailItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_tv_24),
                                                    contentDescription = "series",
                                                )
                                            }, label = { Text("Séries") },
                                            selected = currentDestination?.hasRoute<SeriesDest>() == true,
                                            onClick = { navController.navigate(SeriesDest()) }
                                        )
                                        NavigationRailItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_person_24),
                                                    contentDescription = "acteurs",
                                                )
                                            }, label = { Text("Acteurs") },
                                            selected = currentDestination?.hasRoute<ActeursDest>() == true,
                                            onClick = { navController.navigate(ActeursDest()) })
                                    }
                                }
                            }
                        }
                    },
                    topBar = {
                        //val searchText by remember { mutableStateOf("") }
                        //val isSearchActive by remember { mutableStateOf(false) }
                        if (!isProfilDest) {
                            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) { // PORTRAIT
                                Surface(
                                    color = Color.Cyan
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(15.dp)
                                    ) {
                                        TextButton(
                                            onClick = { navController.navigate(ProfilDest()) },
                                            colors = ButtonDefaults.textButtonColors(Color.Blue)
                                        ) {
                                            Text(
                                                "Home",
                                                color = Color.White
                                            )
                                        }
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_search_24),
                                            contentDescription = "recherche",
                                            modifier = Modifier
                                                .size(30.dp)
                                                .clickable { navController.navigate(SearchDest()) },
                                        )

                                    }
                                }
                            } else { // PAYSAGE top bar
                                FloatingActionButton(
                                    onClick = { navController.navigate(SearchDest()) },
                                    containerColor = Color.Cyan,
                                    modifier = Modifier
                                        .padding(150.dp)
                                        .clip(CircleShape)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_search_24),
                                        contentDescription = "recherche",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        }
                    },
                )

                { innerPadding ->
                    NavHost(
                        navController, startDestination = ProfilDest(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<ProfilDest> {
                            Screen(
                                windowSizeClass,
                                navController
                            )
                        }
                        composable<FilmsDest> { FilmsScreen(viewmodel, navController) }
                        composable<SeriesDest> { SeriesScreen(viewmodel) }
                        composable<ActeursDest> { ActeursScreen(viewmodel) }
                        composable<SearchDest> { SearchScreen(viewmodel) }
                        composable("MovieDetails/{movie.id}") { backStackEntry ->
                            val movieId =
                                backStackEntry.arguments?.getString("movie.id") ?: return@composable
                            movieDetails(viewmodel, movieId, navController)
                        }
                    }
                }
            }
        }
    }
}










