package com.cide.practica_compose_placecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.TextButton
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
    val seattle = Place(
        imagen = R.drawable.seattlefoto,
        nombreFoto = "Foto de Seattle",
        titulo = "Seattle",
        subtitulo = "Washington - Estados Unidos",
        descripcion = "Seattle destaca por sus paisajes naturales, sus rutas junto al agua y las vistas de las montañas que rodean la ciudad.",
        nombreDificultad = "Media",
        numeroDificultad = 3,
        valoracion = 8.4,
        nombrePopularidad = "Ruta destacada",
        progresso = 0.75f
    )
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_Compose_PlaceCardTheme {
                PlaceCard(PlaceRepository.seattle)
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
            PlaceRating(place.valoracion, modifier = modifier
                .align(Alignment.BottomStart)
                .padding(40.dp, 20.dp))
            PlaceSuperText(place.nombreFoto, modifier = modifier
                .align(Alignment.Center)
                .padding(0.dp,40.dp,0.dp,0.dp))
            PlaceNombrePopularidad(place.nombrePopularidad, modifier = modifier
                .align(Alignment.BottomStart)
                .padding(100.dp, 31.dp))
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
        modifier = modifier
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
            .background(colorResource(R.color.white))
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "%.1f".format(valoracion),
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.teal_700)
        )
    }
}

@Composable
fun PlaceSuperText(nombreFoto: String, modifier: Modifier= Modifier){
    Text(
        text = nombreFoto,
        style = MaterialTheme.typography.headlineSmall,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}

@Composable
fun PlaceNombrePopularidad(nombrePopularida: String, modifier: Modifier= Modifier){
    Text(
        text = nombrePopularida,
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}

@Composable
fun PlaceHeader(titulo: String,subtitulo: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(15.dp,0.dp)
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
        modifier = modifier.padding(15.dp,0.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun PlaceDifficulty(dificultad: Int, nombreDificultad: String,modifier: Modifier = Modifier){
    Row (
        modifier = modifier.padding(15.dp,0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dificultad: $nombreDificultad  ",
            style = MaterialTheme.typography.bodyLarge
        )
        val color = MaterialTheme.colorScheme.primary

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
            modifier = modifier.fillMaxWidth(),
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
        modifier = modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(20.dp)),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
fun PlaceActions (modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            modifier = modifier.padding(0.dp,2.dp,0.dp,5.dp),
            horizontalArrangement = Arrangement.spacedBy(
                40.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //boton Guardar
            Button(
                {},
                modifier.weight(0.8f),
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
                modifier = modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colorResource(R.color.teal_700)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = "compartir"
                )
                Spacer(modifier.width(10.dp))
                Text(
                    "Compartir",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        //boton mas informacion
        TextButton(
            {}
        ) {
            Text(
                "Mas informacion",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Practica_Compose_PlaceCardTheme {
        PlaceCard(PlaceRepository.seattle)
    }
}