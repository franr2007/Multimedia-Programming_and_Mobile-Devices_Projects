package com.example.practicagestoractividades_viewmodel.data.model

data class Actividad(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val dificultad: String,
    val materialIncluido: Boolean,
    val gratuita: Boolean,
    val precio: Double
)
