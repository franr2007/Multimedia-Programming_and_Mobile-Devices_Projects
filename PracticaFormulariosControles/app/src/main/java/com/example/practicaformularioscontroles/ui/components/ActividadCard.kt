package com.example.practicaformularioscontroles.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicaformularioscontroles.data.model.Actividad
import java.util.Locale

@Composable
fun ActividadCard(
    actividad: Actividad,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Nombre de la actividad.
            Text(
                text = actividad.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            // Categoría y dificultad.
            Text(
                text = "${actividad.categoria} · ${actividad.dificultad}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Descripción recibida del objeto.
            Text(
                text = actividad.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            // Se muestra el precio o la indicación de gratuidad.
            if (actividad.gratuita) {
                Text(
                    text = "Gratuïta",
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                Text(
                    text = String.format(
                        Locale("es", "ES"),
                        "%.2f €",
                        actividad.precio
                    ),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Solo mostramos esta indicación cuando incluye material.
            if (actividad.materialIncluido) {
                Text(
                    text = "Material inclòs",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}