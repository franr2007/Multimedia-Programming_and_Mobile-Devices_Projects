package com.example.practicaformularioscontroles.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicaformularioscontroles.data.model.Actividad
import com.example.practicaformularioscontroles.ui.components.ActividadCard
import com.example.practicaformularioscontroles.ui.components.FormularioActividad

@Composable
fun GestorActividadesScreen() {

    // Estado principal de la lista.
    // mutableStateOf permite que Compose detecte los cambios.
    // La lista comienza vacía, tal como pide la actividad.
    var actividad by remember {
        mutableStateOf(listOf<Actividad>())
    }

    // LazyColumn permite mostrar el formulario y las actividades
    // en una misma lista desplazable, evitando problemas de espacio.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Primer elemento: formulario de creación.
        item {
            FormularioActividad(
                onGuardar = { nuevaActividad ->

                    // Se asigna un identificador único a cada actividad.
                    val nuevoId = (actividad.maxOfOrNull { it.id } ?: 0) + 1

                    // Se crea una nueva lista con la actividad añadida.
                    // Esta asignación provoca la recomposición de Compose.
                    actividad = actividad + nuevaActividad.copy(
                        id = nuevoId
                    )
                }
            )
        }

        // Segundo elemento: título de la lista.
        item {
            Text(
                text = "ACTIVIDADES CREADAS: ",
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Cada actividad genera automáticamente una tarjeta.
        // La clave estable permite identificar correctamente cada elemento.
        items(
            items = actividad,
            key = { actividad -> actividad.id }
        ) { actividad ->

            ActividadCard(
                actividad = actividad
            )
        }
    }
}