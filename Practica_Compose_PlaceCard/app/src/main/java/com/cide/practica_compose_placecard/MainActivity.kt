package com.cide.practica_compose_placecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cide.practica_compose_placecard.model.PlaceRepository
import com.cide.practica_compose_placecard.ui.components.PlaceCard
import com.cide.practica_compose_placecard.ui.theme.Practica_Compose_PlaceCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_Compose_PlaceCardTheme {
                //Funcion para mostrar la PlaceCard
                PlaceCard(PlaceRepository.seattle)
            }
        }
    }
}

//Previsualizable
@Preview(showBackground = true)
@Composable
fun Preview() {
    Practica_Compose_PlaceCardTheme {
        PlaceCard(PlaceRepository.seattle)
    }
}