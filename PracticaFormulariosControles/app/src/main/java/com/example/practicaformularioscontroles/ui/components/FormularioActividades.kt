package com.example.practicaformularioscontroles.ui.components

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practicaformularioscontroles.data.model.Actividad

@Composable
fun FormularioActividad(
    onGuardar: (Actividad) -> Unit,
    modifier: Modifier = Modifier
) {

    //Variables
    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf("") }
    var dificultad by rememberSaveable { mutableStateOf("Facil") }
    var materialIncluido by rememberSaveable { mutableStateOf(false) }
    var gratuita by rememberSaveable { mutableStateOf(false) }
    var precio by rememberSaveable { mutableStateOf("") }

    //variable para intentar guardar
    var intentarGuardar by rememberSaveable { mutableStateOf(false) }


    //validaciones

    val formatoPrecio = precio.toDoubleOrNull()
    val validarNombre = nombre.trim().length in 3..30
    val validarDescripcion = descripcion.trim().length in 10..120
    val validarCategoria = categoria.isNotBlank()
    val validarPrecio = gratuita || (formatoPrecio != null && formatoPrecio > 0.0)

    // validacion que recoge todas las validaciones, solo se podra guardar si esta da true
    val validarFormulario = validarNombre &&
            validarDescripcion &&
            validarCategoria &&
            validarPrecio

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "NUEVA ACTIVIDAD",
            style = MaterialTheme.typography.headlineSmall
        )

        //campo de texto para el nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = intentarGuardar && !validarNombre,
            supportingText = {
                if (intentarGuardar && !validarNombre) {
                    Text("El nombre ha de tener entre 3 y 30 caracteres")
                }
            }
        )

        //campo de texto para la descripcion
        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            maxLines = 4,
            isError = intentarGuardar && !validarDescripcion,
            supportingText = {
                if (intentarGuardar && !validarDescripcion) {
                    Text("La descripcion ha de tener entre 10 i 120 caracteres")
                }
            }
        )

        //selector de categorias
        SelectorCategoria(
            categoria = categoria,
            onCategoriaSeleccionada = { categoria = it },
        )

        if (intentarGuardar && !validarCategoria) {
            Text(
                text = "Debes seleccionar una categoria",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 14.dp)
            )
        }

        //selector de dificultades
        SelectorDificultad(
            dificultad = dificultad,
            ondificultadeseleccionada = { dificultad = it }
        )

        // Checkbox que indica si la actividad lleva material
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {materialIncluido = !materialIncluido},
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = materialIncluido,
                onCheckedChange = { materialIncluido = it }
            )

            Text("Material incluido")
        }

        // Switch si la actividad es gratuita
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { gratuita = !gratuita },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Actividad Gratuita:")

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

        //campo de texto de precio
        OutlinedTextField(
            value = precio,
            onValueChange = { nuevoPrecio ->
                precio = nuevoPrecio.replace(',', '.')
            },
            label = { Text("Precio:") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !gratuita,
            isError = intentarGuardar && !validarPrecio,
            supportingText = {
                if (gratuita) {
                    Text("Actividad gratuita: 0,00 €")
                } else if (intentarGuardar && !validarPrecio) {
                    Text("Introduce un precio numerico superior a 0")
                }
            }
        )

        //Boton que valida el formulario y guarda la actividad
        Button(
            onClick = {
                intentarGuardar = true

                if (validarFormulario) {
                    val novaActivitat = Actividad(
                        id = 0,
                        nombre = nombre.trim(),
                        descripcion = descripcion.trim(),
                        categoria = categoria,
                        dificultad = dificultad,
                        materialIncluido = materialIncluido,
                        gratuita = gratuita,
                        precio = if (gratuita) 0.0 else formatoPrecio!!
                    )

                    onGuardar(novaActivitat)

                    //Se reinician todos los estados
                    nombre = ""
                    descripcion = ""
                    categoria = ""
                    dificultad = "Facil"
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


//Composable para seleccionar una categoria
@Composable
fun SelectorCategoria(
    categoria: String,
    onCategoriaSeleccionada: (String) -> Unit
) {
    //Controla si el menú está abierto o cerrado.
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
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

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
            style = MaterialTheme.typography.titleMedium
        )

        dificultades.forEach { opcion ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        ondificultadeseleccionada(opcion)
                    }
                    .padding(vertical = 2.dp),
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
