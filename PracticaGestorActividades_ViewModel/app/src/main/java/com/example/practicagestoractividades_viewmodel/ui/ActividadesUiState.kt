
package com.example.practicagestoractividades_viewmodel.ui

import com.example.practicagestoractividades_viewmodel.data.model.Actividad

data class ActividadesUiState(
    //lista actividades
    val actividades: List<Actividad> = emptyList(),

    //variables form
    val nombre: String = "",
    val descripcion: String = "",
    val categoria: String = "",
    val dificultad: String = "Facil",
    val materialIncluido: Boolean = false,
    val gratuita: Boolean = false,
    val precio: String = "",

    //control errores
    val intentarGuardar: Boolean = false
) {
    //validaciones form
    val validarNombre: Boolean
        get() = nombre.trim().length in 3..30

    val validarDescripcion: Boolean
        get() = descripcion.trim().length in 10..120

    val validarCategoria: Boolean
        get() = categoria.isNotBlank()

    val validarPrecio: Boolean
        get() = gratuita || ((precio.toDoubleOrNull() ?: 0.0) > 0.0)

    val validarFormulario: Boolean
        get() = validarNombre &&
                validarDescripcion &&
                validarCategoria &&
                validarPrecio
}
