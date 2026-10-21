package com.cide.practica_compose_placecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cide.practica_compose_placecard.model.Place
import com.cide.practica_compose_placecard.ui.theme.Practica_Compose_PlaceCardTheme

object PlaceRepository{
    val saCalobra = Place(
        imagen =  R.drawable.sierratramuntana,
        nombreFoto = "Foto Sa Calobra",
        titulo = "Sa calobra",
        subtitulo = "Escorca -serra de Tramuntana",
        descripcion = "descripcion ajsdjasdjfjasdfjsadjf",
        dificultad = "Media",
        valoracion = 4.3,
        progresso = 75.42f
    )
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_Compose_PlaceCardTheme {
                PlaceCard(PlaceRepository.saCalobra)
            }
        }
    }
}

@Composable
fun PlaceCard(place: Place, modifier: Modifier = Modifier){
    Column {
        PlaceImage(place.imagen)
        PlaceHeader(place.titulo,place.subtitulo)
        PlaceDescription(place.descripcion)
        PlaceDifficulty(place.dificultad)
        PlaceBarProgress()
        PlaceHorizontalDivider()
        PlaceActions()
    }
}

@Composable
fun PlaceImage (imagen: Int) {
    Image(
        painter = painterResource(imagen),
        contentDescription = "Fotografia de la sierra de tramuntana",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(23.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PlaceHeader(titulo: String,subtitulo: String){
    Column {
        Text(
            text = titulo
        )
        Text(
            text = subtitulo
        )
    }
}
@Composable
fun PlaceDescription(descripcion: String){
    Text(
        text = descripcion
    )
}

@Composable
fun PlaceDifficulty(dificultad: String){
    Row {
        Text("Dificultad: ")
        Text(
            text = "Dificultad: $dificultad"
        )
    }
}

@Composable
fun PlaceBarProgress(){

}

@Composable
fun PlaceHorizontalDivider(){

}

@Composable
fun PlaceActions (modifier: Modifier = Modifier){
    Row (modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button({}) {
            Icon(painter = painterResource(R.drawable.ic_favorites), "favoritos")
            Spacer(modifier.width(10.dp))
            Text("Guardar")
        }
        Button({}) {
            Icon(painter = painterResource(R.drawable.ic_share), "compartir")
            Spacer(modifier.width(10.dp))
            Text("Compartir")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Practica_Compose_PlaceCardTheme {
        PlaceCard(PlaceRepository.saCalobra)
    }
}