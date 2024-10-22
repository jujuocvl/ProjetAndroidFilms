package com.example.myfirstapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myfirstapplication.FilmsDest
import com.example.myfirstapplication.R

@Composable
fun Screen(classes: WindowSizeClass, navController:NavController) {
    when (classes.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> { //PORTRAIT
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Presentation()
                Socials()
                Bouton(navController)
            }
        }

        else -> { //PAYSAGE
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Presentation()
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Socials()
                    Bouton(navController)
                }
            }
        }
    }
}

@Composable
fun Presentation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.accueil_1_saphir_m),
            contentDescription = "PP",
            modifier = Modifier
                .size(175.dp)
                .border(BorderStroke(2.dp, Color.Cyan), CircleShape)
                .clip(CircleShape)
                .scale(1.25f)
        )
        Spacer(Modifier.height(20.dp))
        Text(text = "Julie ORCIVAL", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        Text(text = "Etudiante en 4ème année")
        Text(textAlign = TextAlign.Center, text = "Ecole d'ingénieurs")
        Text(text = "en informatique pour la santé")
        Text(text = "ISIS partenaire INSA", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(35.dp))
    }
}

@Composable
fun Socials() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column() {
            Icon(
                painter = painterResource(R.drawable.baseline_email_24),
                contentDescription = "mail",
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(R.drawable.baseline_link_24),
                contentDescription = "link",
                modifier = Modifier.size(24.dp)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "julie.orcival@gmail.com")
            Text(text = "www.linkedin.com/in/julie-orcival")
        }
    }
}
@Composable
fun Bouton(navController: NavController) {
    Spacer(modifier = Modifier.size(32.dp))
    Button(
        onClick = { navController.navigate(FilmsDest()) },
    ) {
        Text(text = "Démarrer")
    }
}