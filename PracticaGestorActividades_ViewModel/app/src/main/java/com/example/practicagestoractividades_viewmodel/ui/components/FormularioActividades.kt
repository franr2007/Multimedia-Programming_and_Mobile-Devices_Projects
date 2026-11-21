package com.example.practicagestoractividades_viewmodel.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practicagestoractividades_viewmodel.ui.ActividadesUiState


@Composable
fun FormularioActividad(
    uiState: ActividadesUiState,
    onNombreChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onCategoriaChange: (String) -> Unit,
    onDificultadChange: (String) -> Unit,
    onMaterialIncluidoChange: (Boolean) -> Unit,
    onGratuitaChange: (Boolean) -> Unit,
    onPrecioChange: (String) -> Unit,
    onGuardar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "NUEVA ACTIVIDAD",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = uiState.nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = uiState.intentarGuardar && !uiState.validarNombre,
            supportingText = {
                if (uiState.intentarGuardar && !uiState.validarNombre) {
                    Text("El nombre ha de tener entre 3 y 30 caracteres")
                }
            }
        )

        TextField(
            value = uiState.descripcion,
            onValueChange = onDescripcionChange,
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 4,
            isError = uiState.intentarGuardar && !uiState.validarDescripcion,
            supportingText = {
                if (uiState.intentarGuardar && !uiState.validarDescripcion) {
                    Text("La descripcion ha de tener entre 10 y 120 caracteres")
                }
            }
        )

        SelectorCategoria(
            categoria = uiState.categoria,
            onCategoriaSeleccionada = onCategoriaChange
        )

        if (uiState.intentarGuardar && !uiState.validarCategoria) {
            Text(
                text = "Debes seleccionar una categoria",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        SelectorDificultad(
            dificultad = uiState.dificultad,
            ondificultadeseleccionada = onDificultadChange
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onMaterialIncluidoChange(!uiState.materialIncluido)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = uiState.materialIncluido,
                onCheckedChange = onMaterialIncluidoChange
            )
            Text("Material incluido")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onGratuitaChange(!uiState.gratuita)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Actividad Gratuita:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Switch(
                checked = uiState.gratuita,
                onCheckedChange = onGratuitaChange
            )
        }

        OutlinedTextField(
            value = uiState.precio,
            onValueChange = onPrecioChange,
            label = { Text("Precio:") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !uiState.gratuita,
            isError = uiState.intentarGuardar && !uiState.validarPrecio,
            supportingText = {
                if (uiState.gratuita) {
                    Text("Actividad gratuita: 0,00 €")
                } else if (uiState.intentarGuardar && !uiState.validarPrecio) {
                    Text("Introduce un precio numerico superior a 0,00 €")
                }
            }
        )

        Button(
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GUARDAR")
        }
    }
}



//Composable para seleccionar una categoria
@Composable
fun SelectorCategoria(
    categoria: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    //controla si el menu esta abierto o cerrado.
    var expanded by remember { mutableStateOf(false) }

    val categorias = listOf(
        "Deportes",
        "Naturaleza",
        "Cocina",
        "Cultura",
        "Otros"
    )

    Column {
        Text(
            text = "Categoria: ",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Boton que abre el menu
        Button(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors =ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Gray,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Gray
            ),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = categoria.ifBlank {
                        "Seleccionar categoria"
                    }
                )

                Text("▼")
            }



            //menu desplegable
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                categorias.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            onCategoriaSeleccionada(opcion)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

//composable para seleccionar una dificultad
@Composable
fun SelectorDificultad(
    dificultad: String,
    ondificultadeseleccionada: (String) -> Unit
) {
    val dificultades = listOf("Facil", "Normal", "Dificil")

    Column {
        Text(
            text = "Dificultad:",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        dificultades.forEach { opcion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        ondificultadeseleccionada(opcion)
                    }
                    .padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dificultad == opcion,
                    onClick = {
                        ondificultadeseleccionada(opcion)
                    }
                )

                Text(opcion)
            }
        }
    }
}
