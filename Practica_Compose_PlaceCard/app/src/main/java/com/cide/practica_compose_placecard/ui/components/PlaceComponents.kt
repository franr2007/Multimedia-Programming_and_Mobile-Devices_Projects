package com.cide.practica_compose_placecard.ui.components

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
import androidx.compose.ui.unit.dp
import com.cide.practica_compose_placecard.R
import com.cide.practica_compose_placecard.model.Place

//Componente para ordenar todas las places
@Composable
fun PlaceCard(place: Place, modifier: Modifier = Modifier){
    //columna para mostar los componentes verticalmente
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        //Hago un box en esta parte para poder sobre poner componentes encima de otros
        Box {
            PlaceImage(place.imagen)
            PlaceRating(place.valoracion, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(40.dp, 20.dp))
            PlaceSuperText(place.nombreFoto, modifier = Modifier
                .align(Alignment.Center)
                .padding(0.dp, 40.dp, 0.dp, 0.dp))
            PlaceNombrePopularidad(place.nombrePopularidad, modifier = Modifier
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

//Componente para la imagen
@Composable
fun PlaceImage (imagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imagen),
        contentDescription = "Fotografia de seattle",
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(23.dp, 50.dp, 23.dp, 10.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}

//Componente para mostrar la valoracion
@Composable
fun PlaceRating(valoracion: Double, modifier: Modifier = Modifier){
    //Crea un cuadrado al rededor del contenido
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

//Componente para el nombre de la foto
@Composable
fun PlaceSuperText(nombreFoto: String, modifier: Modifier= Modifier){
    Text(
        text = nombreFoto,
        style = MaterialTheme.typography.headlineSmall,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}

//Componente para el nombre de tipo de Popularidad
@Composable
fun PlaceNombrePopularidad(nombrePopularida: String, modifier: Modifier= Modifier){
    Text(
        text = nombrePopularida,
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}

// Componente para la cabezera
@Composable
fun PlaceHeader(titulo: String,subtitulo: String, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.padding(15.dp,0.dp)
    ) {
        //titulo
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineLarge,
            color = colorResource(R.color.teal_700)
        )
        Row (verticalAlignment = Alignment.CenterVertically){
            //Punto
            Icon(
                painter = painterResource(R.drawable.ic_ubi),
                contentDescription = "punto_ubi",
                tint = Color.Gray,
                modifier = Modifier.size(10.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            //Subtitulo
            Text(
                text = subtitulo,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }
}

//Componente para la descripcion
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

//Componente para la dificultad
@Composable
fun PlaceDifficulty(dificultad: Int, nombreDificultad: String,modifier: Modifier = Modifier){
    //Ordena el contenido en horizontal (en fila)
    Row (
        modifier = modifier.padding(15.dp,0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Texto dificultad
        Text(
            text = "Dificultad: $nombreDificultad  ",
            style = MaterialTheme.typography.bodyLarge
        )
        val color = MaterialTheme.colorScheme.primary

        //Canva para crear los circulos
        Canvas(modifier = Modifier.size(width = 100.dp, height = 16.dp)) {
            val radius = size.height / 2
            val gap = 8.dp.toPx()

            repeat(5) { i ->
                drawCircle(
                    color = color,
                    radius = radius,
                    center = Offset(radius + i * (radius * 2 + gap), radius),
                    //condicion que pinta los ciruclos segun en numero de la varibale del objeto
                    style = if (i < dificultad) Fill else Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}

//Componente para la barra de progreso
@Composable
fun PlaceBarProgress(progreso: Float, modifier: Modifier = Modifier) {
    //CoerceIN coje el numero de la variable y limita minimo y maximo
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
                text = "Ruta completada:  ",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                //pasa el numero a una unidad legible y lo pasa como texto
                text = "${(limiteProgreso * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        //Barra de progresso
        LinearProgressIndicator(
            progress = { limiteProgreso },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }
}

//Componete para la barra divisora
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

//Componentes para los botones
@Composable
fun PlaceActions (modifier: Modifier = Modifier){
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row(
            modifier = Modifier.padding(0.dp,2.dp,0.dp,5.dp),
            horizontalArrangement = Arrangement.spacedBy(
                40.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //boton Guardar
            Button(
                {},
                Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.teal_700)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                Icon(painter = painterResource(R.drawable.ic_favorites), "favoritos")
                Spacer(Modifier.width(10.dp))
                Text(
                    "Guardar",
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
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = "compartir"
                )
                Spacer(Modifier.width(10.dp))
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