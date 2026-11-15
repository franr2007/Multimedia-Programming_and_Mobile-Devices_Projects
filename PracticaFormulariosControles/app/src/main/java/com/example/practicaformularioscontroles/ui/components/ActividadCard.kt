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
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            //nombre
            Text(
                text = actividad.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            //categoria y dificultad
            Text(
                text = "${actividad.categoria} · ${actividad.dificultad}",
                style = MaterialTheme.typography.bodyMedium
            )

            //descripcion
            Text(
                text = actividad.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )

            // precio o info de si es gratuita
            if (actividad.gratuita) {
                Text(
                    text = "Gratuita",
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

            // info material
            if (actividad.materialIncluido) {
                Text(
                    text = "Material incluido",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}