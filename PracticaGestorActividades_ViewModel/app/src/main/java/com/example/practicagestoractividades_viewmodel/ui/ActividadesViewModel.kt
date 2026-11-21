
package com.example.practicagestoractividades_viewmodel.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practicagestoractividades_viewmodel.data.model.Actividad

class ActividadesViewModel : ViewModel() {

    // estado principal de la pantalla
    var uiState by mutableStateOf(ActividadesUiState())
        private set

    // actualiza los campos del formulario
    fun onNombreChange(valor: String) {
        uiState = uiState.copy(nombre = valor)
    }

    fun onDescripcionChange(valor: String) {
        uiState = uiState.copy(descripcion = valor)
    }

    fun onCategoriaChange(valor: String) {
        uiState = uiState.copy(categoria = valor)
    }

    fun onDificultadChange(valor: String) {
        uiState = uiState.copy(dificultad = valor)
    }

    fun onMaterialIncluidoChange(valor: Boolean) {
        uiState = uiState.copy(materialIncluido = valor)
    }

    fun onGratuitaChange(valor: Boolean) {
        uiState = uiState.copy(
            gratuita = valor,
            precio = if (valor) "" else uiState.precio
        )
    }

    fun onPrecioChange(valor: String) {
        uiState = uiState.copy(precio = valor.replace(",", "."))
    }

    // valida y guarda una actividad
    fun guardarActividad() {
        uiState = uiState.copy(intentarGuardar = true)

        if (!uiState.validarFormulario) return

        val nuevaActividad = Actividad(
            id = (uiState.actividades.maxOfOrNull { it.id } ?: 0) + 1,
            nombre = uiState.nombre.trim(),
            descripcion = uiState.descripcion.trim(),
            categoria = uiState.categoria,
            dificultad = uiState.dificultad,
            materialIncluido = uiState.materialIncluido,
            gratuita = uiState.gratuita,
            precio = if (uiState.gratuita) 0.0 else uiState.precio.toDouble()
        )

        // añade la actividad y reinicia el formulario
        uiState = ActividadesUiState(
            actividades = uiState.actividades + nuevaActividad
        )
    }

    // elimina una actividad por su id
    fun eliminarActividad(id: Int) {
        uiState = uiState.copy(
            actividades = uiState.actividades.filterNot { it.id == id }
        )
    }
}
