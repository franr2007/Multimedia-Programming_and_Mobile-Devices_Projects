package com.example.practicaformularioscontroles.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicaformularioscontroles.data.model.Actividad

@Composable
fun FormularioActividad(
    onGuardar: (Actividad) -> Unit,
    modifier: Modifier = Modifier
) {

    // Estados de los campos del formulario.
    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf("") }
    var dificultad by rememberSaveable { mutableStateOf("Fàcil") }
    var materialIncluido by rememberSaveable { mutableStateOf(false) }
    var gratuita by rememberSaveable { mutableStateOf(false) }
    var precio by rememberSaveable { mutableStateOf("") }

    // Solo mostramos los errores después de intentar guardar.
    var intentarGuardar by rememberSaveable { mutableStateOf(false) }

    // Conversión segura del precio: si no es numérico devuelve null.
    val precioNumerico = precio.replace(',', '.').toDoubleOrNull()

    // Validaciones exigidas en la actividad.
    val nombreValido = nombre.trim().length in 3..30
    val descripcionValida = descripcion.trim().length in 10..120
    val categoriaValida = categoria.isNotBlank()
    val precioValido = gratuita || (precioNumerico != null && precioNumerico > 0.0)

    // Solo se permite guardar cuando todos los campos son válidos.
    val formulariValid = nombreValido &&
            descripcionValida &&
            categoriaValida &&
            precioValido

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "NOVA ACTIVITAT",
            style = MaterialTheme.typography.headlineSmall
        )

        // Nombre: debe contener entre 3 y 30 caracteres.
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = intentarGuardar && !nombreValido,
            supportingText = {
                if (intentarGuardar && !nombreValido) {
                    Text("El nom ha de tenir entre 3 i 30 caràcters")
                }
            }
        )

        // Descripción: debe contener entre 10 y 120 caracteres.
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripció") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 4,
            isError = intentarGuardar && !descripcionValida,
            supportingText = {
                if (intentarGuardar && !descripcionValida) {
                    Text("La descripció ha de tenir entre 10 i 120 caràcters")
                }
            }
        )

        // Selector desplegable de categoría.
        SelectorCategoria(
            categoria = categoria,
            onCategoriaSeleccionada = { categoria = it },
            mostrarError = intentarGuardar && !categoriaValida
        )

        // Selector de dificultad mediante RadioButtons.
        SelectorDificultat(
            dificultat = dificultad,
            onDificultatSeleccionada = { dificultad = it }
        )

        // Checkbox: indica si la actividad incluye material.
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = materialIncluido,
                onCheckedChange = { materialIncluido = it }
            )

            Text("Material inclòs")
        }

        // Switch: al activarlo, el precio pasa a 0 y se deshabilita.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Activitat gratuïta")

            Switch(
                checked = gratuita,
                onCheckedChange = {
                    gratuita = it

                    if (it) {
                        precio = "0.00"
                    }
                }
            )
        }

        // El precio solamente se puede modificar si no es gratuita.
        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Preu (€)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !gratuita,
            isError = intentarGuardar && !precioValido,
            supportingText = {
                if (gratuita) {
                    Text("Activitat gratuïta: 0,00 €")
                } else if (intentarGuardar && !precioValido) {
                    Text("Introdueix un preu numèric superior a 0")
                }
            }
        )

        // Botón que valida, crea el objeto y lo envía a la pantalla principal.
        Button(
            onClick = {
                intentarGuardar = true

                if (formulariValid) {
                    val novaActivitat = Actividad(
                        id = 0,
                        nombre = nombre.trim(),
                        descripcion = descripcion.trim(),
                        categoria = categoria,
                        dificultad = dificultad,
                        materialIncluido = materialIncluido,
                        gratuita = gratuita,
                        precio = if (gratuita) 0.0 else precioNumerico!!
                    )

                    onGuardar(novaActivitat)

                    // Se reinician todos los estados tras guardar correctamente.
                    nombre = ""
                    descripcion = ""
                    categoria = ""
                    dificultad = "Fàcil"
                    materialIncluido = false
                    gratuita = false
                    precio = ""
                    intentarGuardar = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("GUARDAR")
        }
    }
}


// Selector reutilizable de categorías mediante DropdownMenu.
@Composable
fun SelectorCategoria(
    categoria: String,
    onCategoriaSeleccionada: (String) -> Unit,
    mostrarError: Boolean
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Opciones disponibles para el usuario.
    // Puedes modificar esta lista y añadir nuevas categorías.
    val categories = listOf(
        "Esport",
        "Natura",
        "Gastronomia",
        "Cultura",
        "Altres"
    )

    Column {
        OutlinedTextField(
            value = categoria,
            onValueChange = {},
            readOnly = true,
            label = { Text("Categoria") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            enabled = true,
            isError = mostrarError,
            supportingText = {
                if (mostrarError) {
                    Text("Selecciona una categoria")
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { opcion ->
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


// Selector reutilizable de dificultad mediante RadioButtons.
@Composable
fun SelectorDificultat(
    dificultat: String,
    onDificultatSeleccionada: (String) -> Unit
) {
    val dificultats = listOf("Fàcil", "Mitjana", "Alta")

    Column {
        Text(
            text = "Dificultat",
            style = MaterialTheme.typography.titleMedium
        )

        dificultats.forEach { opcio ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onDificultatSeleccionada(opcio)
                    }
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dificultat == opcio,
                    onClick = {
                        onDificultatSeleccionada(opcio)
                    }
                )

                Text(opcio)
            }
        }
    }
}
