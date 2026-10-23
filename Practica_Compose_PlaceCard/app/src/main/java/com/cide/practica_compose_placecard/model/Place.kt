package com.cide.practica_compose_placecard.model

//Clase de datos para que sea mas facil crear PlaceCards
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
