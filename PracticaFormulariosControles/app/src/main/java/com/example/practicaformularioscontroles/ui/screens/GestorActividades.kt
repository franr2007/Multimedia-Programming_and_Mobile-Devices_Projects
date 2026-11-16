package com.example.practicaformularioscontroles.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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

    //lista vacia de actividades
    var actividades by remember {
        mutableStateOf(listOf<Actividad>())
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        //formulario para crear la actividad
        item {
            FormularioActividad(
                onGuardar = { nuevaActividad ->

                    //variable para asignar una id a una actividad
                    val nuevoId = (actividades.maxOfOrNull { it.id } ?: 0) + 1

                    //le suma una copia de la actividad a la que le pasamos un nuevo id
                    actividades = actividades + nuevaActividad.copy(
                        id = nuevoId
                    )
                }
            )
        }

        item {
            Text(
                text = "ACTIVIDADES CREADAS: ",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        //por cada actividad en la lista actividades se crea una card de actividades
        items(
            items = actividades,
            key = { actividad -> actividad.id }
        ) { actividad ->

            ActividadCard(
                actividad = actividad
            )
        }
    }
}