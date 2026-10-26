package com.cide.practica_compose_placecard.model

import android.media.Image

//Clase de datos para que sea mas facil crear PlaceCards
data class Place(
    val listaImagenes: List<Int>,
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
