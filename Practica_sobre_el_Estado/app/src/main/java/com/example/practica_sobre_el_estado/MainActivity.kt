package com.example.practica_sobre_el_estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica_sobre_el_estado.ui.theme.Practica_sobre_el_EstadoTheme

// ---------- DADES PROPORCIONADES ----------

data class Activitat(
    val id: Int,
    val nom: String,
    val durada: String,
    val preu: Double
)

val activitats = listOf(
    Activitat(1, "Snorkel", "1 h", 0.0),
    Activitat(2, "Caiac", "90 min", 25.0),
    Activitat(3, "Senderisme", "2 h", 0.0),
    Activitat(4, "Visita guiada", "1 h", 12.0),
    Activitat(5, "Paddle surf", "1 h", 18.0),
    Activitat(6, "Fotografia", "45 min", 0.0),
    Activitat(7, "Ruta en bicicleta", "3 h", 22.0),
    Activitat(8, "Observació d'aus", "90 min", 0.0),
    Activitat(9, "Busseig", "2 h", 45.0),
    Activitat(10, "Museu", "1 h", 8.0),
    Activitat(11, "Ioga a la platja", "1 h", 0.0),
    Activitat(12, "Excursió en vaixell", "2 h", 35.0),
    Activitat(13, "Taller de cuina", "2 h", 30.0),
    Activitat(14, "Ruta urbana", "90 min", 0.0),
    Activitat(15, "Escalada", "2 h", 28.0),
    Activitat(16, "Pícnic", "1 h", 0.0),
    Activitat(17, "Tast gastronòmic", "90 min", 24.0),
    Activitat(18, "Meditació", "45 min", 0.0),
    Activitat(19, "Ruta en 4x4", "2 h", 40.0),
    Activitat(20, "Visita al mercat", "1 h", 0.0)
)
// ---------- EXEMPLE: CARD CLICKABLE ----------
// Aquesta part es dona perquè Modifier.clickable encara no s'ha treballat.
// La Card NO té remember: rep l'estat i envia un event cap al pare.

@Composable
fun ActivitatCard(
    activitat: Activitat,
    seleccionada: Boolean,
    onCanviSeleccio: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onCanviSeleccio()
            },
        colors = CardDefaults.cardColors(
            containerColor = if (seleccionada) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = activitat.nom,
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = activitat.durada)

            Text(
                text = if (activitat.preu == 0.0) {
                    "Gratuïta"
                } else {
                    "${activitat.preu} €"
                }
            )

            if (seleccionada) {
                Text(
                    text = "✓ Seleccionada",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// ---------- CONTRACTE RECOMANAT PER AL SWITCH ----------
// Implementa aquest component amb Text + Switch.
// IMPORTANT: no hi posis remember; l'estat viu a PantallaActivitats.


@Composable
fun FiltreGratuites(
    activat: Boolean,
    onCanvi: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Solo Gratuitas: ",
            style = MaterialTheme.typography.titleLarge
        )
        Switch(
            checked = activat,
            onCheckedChange =  { cambio ->
                onCanvi(cambio)
            }
        )
    }
}


// ---------- PISTA D'ESTAT DE LA PANTALLA ----------
// A PantallaActivitats necessitaràs, com a mínim:
//
// var nomesGratuites by rememberSaveable { mutableStateOf(false) }
// var idsElegits by remember { mutableStateOf(listOf<Int>()) }
//
// A partir d'aquí has de construir:
// 1. la llista visible segons el Switch
// 2. el LazyVerticalGrid
// 3. la selecció/deselecció en fer click a ActivitatCard
// 4. la llista d'activitats elegides
// 5. el cost total

@Composable
fun ElegidaCard(nombreActivitat: String){
    Text(
        text = nombreActivitat,
    )
}

@Composable
fun TodasElegidas(actividadesElegidas: List<Activitat>){



    Card(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(actividadesElegidas) {activitad ->
                ElegidaCard(activitad.nom)
            }
        }
    }
}

@Composable
fun TotalCard(precioTotal: Double){
    Text(
        text = "TOTAL: $precioTotal€"
    )
}

//Composable para unir todos los componentes de actividad
@Composable
fun PantallaActivitats(){
    //variable desencadenante del switch de filtro de Gratuitos
    var soloGratuitas by rememberSaveable {
        mutableStateOf(false)
    }
    //variable que guardara los cards elegidos por id
    var idElegidos by remember {
        mutableStateOf(listOf<Int>())
    }

    fun canviSeleccio(id: Int){
        idElegidos = if (id in idElegidos) {
            idElegidos - id
        }
        else {
            idElegidos + id
        }
    }

    val activitatsVisibles = if (soloGratuitas) {
        activitats.filter { it.preu == 0.0 }
    } else {
        activitats
    }

    val actividadesElegidas = activitats.filter {
        it.id in idElegidos
    }

    val precioTotal = actividadesElegidas.sumOf {
        it.preu
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "ACTIVIDADES",
                modifier = Modifier.padding(top = 20.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            FiltreGratuites(
                activat = soloGratuitas,
                onCanvi = { cambio -> soloGratuitas = cambio }
            )
        }

        items(activitatsVisibles) { actividad ->
            ActivitatCard(
                activitat = actividad,
                seleccionada = actividad.id in idElegidos,
                onCanviSeleccio = { canviSeleccio(actividad.id) }
            )
        }

        item (span = { GridItemSpan(maxLineSpan)}) {
            Text(
                text = "ELEGIDAS: ",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            TodasElegidas(actividadesElegidas)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            TotalCard(precioTotal)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_sobre_el_EstadoTheme {
                PantallaActivitats()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActividadesPreview() {
    Practica_sobre_el_EstadoTheme {
        PantallaActivitats()
    }
}