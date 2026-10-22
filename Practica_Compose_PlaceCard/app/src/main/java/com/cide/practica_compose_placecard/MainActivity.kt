package com.cide.practica_compose_placecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
        descripcion = "Esta montaña es una de las rutas mas concurridas por los turitas en verano no se que no se cuanto",
        nombreDificultad = "Media",
        numeroDificultad = 3,
        valoracion = 8.4,
        progresso = 0.7f
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
    Column (
        verticalArrangement = Arrangement.spacedBy(20.dp),

    ) {
        Box {
            PlaceImage(place.imagen)
            PlaceRating(place.valoracion, modifier = modifier.align(Alignment.BottomStart).padding(40.dp, 20.dp))
        }
        PlaceHeader(place.titulo,place.subtitulo)
        PlaceDescription(place.descripcion)
        PlaceDifficulty(place.numeroDificultad, place.nombreDificultad)
        PlaceBarProgress(place.progresso)
        PlaceHorizontalDivider()
        PlaceActions()
    }
}

@Composable
fun PlaceImage (imagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imagen),
        contentDescription = "Fotografia de la sierra de tramuntana",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(23.dp, 50.dp, 23.dp, 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PlaceRating(valoracion: Double, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.teal_700)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "%.1f".format(valoracion),
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

@Composable
fun PlaceHeader(titulo: String,subtitulo: String, modifier: Modifier = Modifier){
    Column(
        modifier = Modifier.padding(15.dp,0.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(R.color.teal_700)
        )
        Text(
            text = subtitulo,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}
@Composable
fun PlaceDescription(descripcion: String, modifier: Modifier = Modifier){
    Text(
        text = descripcion,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(15.dp,0.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun PlaceDifficulty(dificultad: Int, nombreDificultad: String,modifier: Modifier = Modifier){
    Row (
        modifier = Modifier.padding(15.dp,0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dificultad: $nombreDificultad  ",
            style = MaterialTheme.typography.bodyLarge
        )
        val color = MaterialTheme.colorScheme.primary   // fuera del Canvas, ver aviso

        Canvas(modifier = modifier.size(width = 100.dp, height = 16.dp)) {
            val radius = size.height / 2
            val gap = 8.dp.toPx()

            repeat(5) { i ->
                drawCircle(
                    color = color,
                    radius = radius,
                    center = Offset(radius + i * (radius * 2 + gap), radius),
                    style = if (i < dificultad) Fill else Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}

@Composable
fun PlaceBarProgress(progreso: Float, modifier: Modifier = Modifier) {
    val limiteProgreso = progreso.coerceIn(0f, 1f)

    Column(
        modifier = modifier.padding(15.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Porcentaje de la ruta: ",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${(limiteProgreso * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        LinearProgressIndicator(
            progress = { limiteProgreso },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

@Composable
fun PlaceHorizontalDivider(modifier: Modifier = Modifier){
    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(20.dp)),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
fun PlaceActions (modifier: Modifier = Modifier){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        horizontalArrangement = Arrangement.spacedBy(
            40.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //boton Guardar
        Button(
            {},
                Modifier.weight(0.8f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.teal_700)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
            Icon(painter = painterResource(R.drawable.ic_favorites), "favoritos")
            Spacer(modifier.width(10.dp))
            Text(
                "Guarda",
                style = MaterialTheme.typography.bodyLarge

            )
        }
        //boton compartir
        OutlinedButton(
            onClick = {},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(R.color.teal_700)
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Icon(painter = painterResource(R.drawable.ic_share), contentDescription = "compartir")
            Spacer(Modifier.width(10.dp))
            Text(
                "Compartir",
                style = MaterialTheme.typography.bodyLarge
            )
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