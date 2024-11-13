package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.view.KeyEventDispatcher
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myfirstapplication.ui.ActeursScreen
import com.example.myfirstapplication.ui.Component
import com.example.myfirstapplication.ui.FilmsScreen
import com.example.myfirstapplication.ui.theme.MyFirstApplicationTheme
import kotlinx.serialization.Serializable
import com.example.myfirstapplication.ui.MainViewModel
import com.example.myfirstapplication.ui.Screen
import com.example.myfirstapplication.ui.SeriesScreen
import com.example.myfirstapplication.ui.movieDetails
import com.example.myfirstapplication.ui.serieDetails
import kotlinx.serialization.json.Json.Default.configuration

@Serializable
class FilmsDest

@Serializable
class ProfilDest

@Serializable
class SeriesDest

@Serializable
class ActeursDest

@Serializable
class FilmDetailDest(val movieId: Int)

@Serializable
class SerieDetailDest(val serieId: Int)

@Serializable
class NewDest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val isProfilDest = currentDestination?.hasRoute<ProfilDest>() == true
            val isPortrait =
                windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT //PORTRAIT
            val viewmodel: MainViewModel by viewModels()
            var searchBar by remember { mutableStateOf(false) }//var état pour visibilité textField

            Scaffold(
                topBar = {
                    if (!isProfilDest) { //pas dans le profil alors continue
                        if (isPortrait) {
                            if (searchBar) {//fonction pour textfield
                                TextField(
                                    value = viewmodel.searchText,
                                    onValueChange = { viewmodel.searchText = it },
                                    placeholder = { Text("Rechercher un film, une série...") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp),

                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                                    keyboardActions = KeyboardActions(
                                        onSearch = {
                                            searchBar = false
                                            viewmodel.SearchMovie()
                                            viewmodel.SearchSerie()
                                            viewmodel.SearchActor()
                                        }
                                    )
                                )
                            } else { //top bar portrait
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
                                                .clickable {
                                                    searchBar = !searchBar
                                                },
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                bottomBar = {
                    if (isPortrait) { // PORTRAIT
                        BottomNavigationBar(navController)
                    }
                },
                floatingActionButton = { //floating button PAYSAGE
                    if (!isPortrait) {
                        if (currentDestination?.hasRoute<ProfilDest>() == false) {
                            FloatingActionButton(
                                onClick = { searchBar = !searchBar },
                                containerColor = Color.Cyan,
                                modifier = Modifier
                                    .clip(CircleShape)
                            ) {
                                if (searchBar) {
                                    TextField(
                                        value = viewmodel.searchText,
                                        onValueChange = { viewmodel.searchText = it },
                                        placeholder = { Text("Rechercher un film, une série...") },
                                        modifier = Modifier
                                            .width(600.dp)
                                            .padding(15.dp),
                                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                                        keyboardActions = KeyboardActions(
                                            onSearch = {
                                                searchBar = false
                                                viewmodel.SearchMovie()
                                                viewmodel.SearchSerie()
                                                viewmodel.SearchActor()
                                            }
                                        )
                                    )
                                }
                            else {
                                Icon(
                                    painterResource(R.drawable.baseline_search_24),
                                    contentDescription = "recherche",
                                )
                            }
                                }
                        }
                    }
                },
            )

            { innerPadding ->
                if (isPortrait) {
                    NavHost(
                        navController, startDestination = ProfilDest(),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<ProfilDest> { Screen(windowSizeClass, navController) }
                        composable<FilmsDest> { FilmsScreen(viewmodel, navController) }
                        composable<SeriesDest> { SeriesScreen(viewmodel, navController) }
                        composable<ActeursDest> { ActeursScreen(viewmodel) }
                        composable<NewDest> { Component(viewmodel, navController) }
                        composable<FilmDetailDest> { backStackEntry ->
                            val movieId =
                                backStackEntry.toRoute<FilmDetailDest>().movieId.toString()
                            movieDetails(viewmodel, movieId, navController)
                        }
                        composable<SerieDetailDest> { backStackEntry ->
                            val serieId =
                                backStackEntry.toRoute<SerieDetailDest>().serieId.toString()
                            serieDetails(viewmodel, serieId, navController)
                        }
                    }
                } else { //PAYSAGE
                    Row(modifier = Modifier.fillMaxSize()) {
                        NavigationRail(navController)
                        NavHost(
                            navController, startDestination = ProfilDest(),
                            Modifier.padding(innerPadding)
                        ) {
                            composable<ProfilDest> { Screen(windowSizeClass, navController) }
                            composable<FilmsDest> { FilmsScreen(viewmodel, navController) }
                            composable<SeriesDest> { SeriesScreen(viewmodel, navController) }
                            composable<ActeursDest> { ActeursScreen(viewmodel) }
                            composable<FilmDetailDest> { backStackEntry ->
                                val movieId =
                                    backStackEntry.toRoute<FilmDetailDest>().movieId.toString()
                                movieDetails(viewmodel, movieId, navController)
                            }
                            composable<SerieDetailDest> { backStackEntry ->
                                val serieId =
                                    backStackEntry.toRoute<SerieDetailDest>().serieId.toString()
                                serieDetails(viewmodel, serieId, navController)
                            }
                        }
                    }

                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) { //botom bar originelle
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        if (currentDestination?.hasRoute<ProfilDest>() == false) {
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
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_new_releases_24),
                            contentDescription = "newcomponent",
                        )
                    }, label = { Text("New") },
                    selected = currentDestination?.hasRoute<NewDest>() == true,
                    onClick = { navController.navigate(NewDest()) }
                )

            }
        }
    }

    @Composable
    fun NavigationRail(navController: NavController) { //botom bar paysage
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        if (currentDestination?.hasRoute<ProfilDest>() == false) {
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










