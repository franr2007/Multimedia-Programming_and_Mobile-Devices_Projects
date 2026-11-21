
package com.example.practicagestoractividades_viewmodel.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicagestoractividades_viewmodel.ui.ActividadesUiState
import com.example.practicagestoractividades_viewmodel.ui.ActividadesViewModel
import com.example.practicagestoractividades_viewmodel.ui.components.ActividadCard
import com.example.practicagestoractividades_viewmodel.ui.components.FormularioActividad

//Route: conecta el ViewModel con la pantalla
@Composable
fun GestorActividadesRoute(
    viewModel: ActividadesViewModel = viewModel()
) {
    GestorActividadesScreen(
        uiState = viewModel.uiState,
        onNombreChange = viewModel::onNombreChange,
        onDescripcionChange = viewModel::onDescripcionChange,
        onCategoriaChange = viewModel::onCategoriaChange,
        onDificultadChange = viewModel::onDificultadChange,
        onMaterialIncluidoChange = viewModel::onMaterialIncluidoChange,
        onGratuitaChange = viewModel::onGratuitaChange,
        onPrecioChange = viewModel::onPrecioChange,
        onGuardar = viewModel::guardarActividad,
        onEliminar = viewModel::eliminarActividad
    )
}

//Screen: muestra la interfaz utilizando el estado recibido
@Composable
fun GestorActividadesScreen(
    uiState: ActividadesUiState,
    onNombreChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onCategoriaChange: (String) -> Unit,
    onDificultadChange: (String) -> Unit,
    onMaterialIncluidoChange: (Boolean) -> Unit,
    onGratuitaChange: (Boolean) -> Unit,
    onPrecioChange: (String) -> Unit,
    onGuardar: () -> Unit,
    onEliminar: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Formulario
        item {
            FormularioActividad(
                uiState = uiState,
                onNombreChange = onNombreChange,
                onDescripcionChange = onDescripcionChange,
                onCategoriaChange = onCategoriaChange,
                onDificultadChange = onDificultadChange,
                onMaterialIncluidoChange = onMaterialIncluidoChange,
                onGratuitaChange = onGratuitaChange,
                onPrecioChange = onPrecioChange,
                onGuardar = onGuardar
            )
        }

        item {
            Text(
                text = "ACTIVIDADES CREADAS:",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        //muestra las actividades guardadas
        items(
            items = uiState.actividades,
            key = { actividad -> actividad.id }
        ) { actividad ->
            ActividadCard(
                actividad = actividad,
                onEliminar = onEliminar
            )
        }
    }
}
