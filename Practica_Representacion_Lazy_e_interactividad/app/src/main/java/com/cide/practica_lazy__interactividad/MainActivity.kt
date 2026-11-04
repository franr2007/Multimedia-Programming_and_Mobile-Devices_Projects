package com.cide.practica_lazy__interactividad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ------------------------------------------------------------
// 1. MODEL DE DADES: LLOC
// ------------------------------------------------------------

data class Lloc(
    val nom: String,
    val categoria: String,
    val imatge: Int
)

// Per començar utilitzam una imatge del sistema com a marcador.
// Podeu substituir-la després pels vostres R.drawable.xxx.
val llocs = listOf(
    Lloc("Cala Mondragó", "Platja", android.R.drawable.ic_menu_gallery),
    Lloc("Castell de Bellver", "Monument", android.R.drawable.ic_menu_gallery),
    Lloc("Sa Calobra", "Ruta", android.R.drawable.ic_menu_gallery),
    Lloc("Cap de Formentor", "Mirador", android.R.drawable.ic_menu_gallery),
    Lloc("Es Trenc", "Platja", android.R.drawable.ic_menu_gallery),
    Lloc("Sóller", "Poble", android.R.drawable.ic_menu_gallery)
)

// ------------------------------------------------------------
// 2. COMPONENT REUTILITZABLE PER A UN LLOC
// ------------------------------------------------------------

@Composable
fun LlocCard(lloc: Lloc) {
    Card(
        modifier = Modifier.width(180.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Image(
                painter = painterResource(lloc.imatge),
                contentDescription = lloc.nom,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = lloc.nom,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lloc.categoria,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

// Composable lloc fila
@Composable
fun LlocFila(lloc: Lloc){
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 7.dp),
                text = lloc.nom,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = lloc.categoria,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// ------------------------------------------------------------
// 3. EXEMPLE GUIAT: LAZYROW
// ------------------------------------------------------------

@Composable
fun LlocsDestacats(llocs: List<Lloc>) {
    LazyRow(
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(llocs) { lloc ->
            LlocCard(lloc)
        }
    }
}

//composable TotsLlocs pinta en lazyColum la parte de todos los lugares
@Composable
fun TotsLlocs(llocs: List<Lloc>) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(llocs) { lloc ->
            LlocFila(lloc)
        }
    }
}

// ------------------------------------------------------------
// 4. PANTALLA DE PARTIDA 1
// ------------------------------------------------------------

@Composable
fun PantallaPractica1() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Llocs destacats",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(12.dp)
        )

        LlocsDestacats(llocs)

        Text(
            text = "Tots els llocs",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(12.dp)
        )

        TotsLlocs(llocs)
        // REPTE 1:
        // Converteix aquesta pantalla en una LazyColumn que contingui:
        //  - el títol "Llocs destacats"
        //  - la LazyRow anterior dins d'un item { }
        //  - el títol "Tots els llocs"
        //  - items(llocs) { lloc -> ... }
        //
        // Crea també un nou composable:
        // fun LlocFila(lloc: Lloc)
    }
}

// ------------------------------------------------------------
// 5. MODEL PER AL REPTE DE LAZYVERTICALGRID
// ------------------------------------------------------------

data class Activitat(
    val nom: String,
    val durada: String,
    val dificultat: String
)

val activitats = listOf(
    Activitat("Snorkel", "1 h", "Fàcil"),
    Activitat("Senderisme", "2 h", "Mitjana"),
    Activitat("Caiac", "90 min", "Mitjana"),
    Activitat("Fotografia", "45 min", "Fàcil"),
    Activitat("Ciclisme", "3 h", "Alta"),
    Activitat("Visita guiada", "1 h", "Fàcil")
)

// REPTE 2:
// 1. Crea ActivitatCard(activitat: Activitat)
// 2. Representa activitats amb LazyVerticalGrid.
// 3. Primera versió: GridCells.Adaptive(140.dp)
// 4. Segona versió: GridCells.Fixed(2)

//ActivitatCard
@Composable
fun ActivitatCard(activitat: Activitat) {
    Card(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = activitat.nom,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = activitat.durada,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = activitat.dificultat,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

//totesActivitats
@Composable
fun TotesActivitats(activitats: List<Activitat>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(activitats) { activitat ->
            ActivitatCard(activitat)
        }
    }
}

//Pantallapractica2
@Composable
fun PantallaPractica2() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Activitats",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(12.dp)
        )

        TotesActivitats(activitats)

    }
}

// ------------------------------------------------------------
// 6. MODEL PER AL REPTE DE LAZYHORIZONTALGRID
// ------------------------------------------------------------

data class Servei(
    val nom: String,
    val detall: String
)

val serveis = listOf(
    Servei("Aparcament", "Gratuït"),
    Servei("Restaurant", "08:00–22:00"),
    Servei("Lavabos", "Disponibles"),
    Servei("Lloguer de caiac", "Des de 15 €"),
    Servei("Dutxes", "Zona platja"),
    Servei("Informació", "Punt turístic")
)

// REPTE 3:
// 1. Crea ServeiCard(servei: Servei)
// 2. Representa serveis amb LazyHorizontalGrid.
// 3. Primera versió: rows = GridCells.Fixed(2)
// 4. Segona versió: rows = GridCells.Adaptive(100.dp)
// 5. Recorda donar una height(...) al grid.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()
                ) {
                    PantallaPractica1()
                    //PantallaPractica2()
                }
            }
        }
    }
}