package com.cide.practica_compose_placecard.model

import androidx.compose.ui.graphics.painter.Painter

data class Place(
    val imagen: Int,
    val nombreFoto: String,
    val titulo: String,
    val subtitulo: String,
    val descripcion: String,
    val valoracion: Double,
    val nombrePopularidad: String,
    val nombreDificultad: String,
    val numeroDificultad: Int,
    val progresso: Float
)
