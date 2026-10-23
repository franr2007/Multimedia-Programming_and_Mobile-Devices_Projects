package com.cide.practica_compose_placecard.model

import com.cide.practica_compose_placecard.R

//Repositorio para guardar distintas Place Cards
object PlaceRepository{
    //Ejemplo de PlaceCard
    val seattle = Place(
        imagen = R.drawable.seattlefoto,
        nombreFoto = "Foto de Seattle",
        titulo = "Seattle",
        subtitulo = "Washington - Estados Unidos",
        descripcion = "Seattle destaca por sus paisajes naturales, sus rutas junto al agua y las vistas de las montañas que rodean la ciudad.",
        nombreDificultad = "Media",
        numeroDificultad = 3,
        valoracion = 8.4,
        nombrePopularidad = "Ruta destacada",
        progresso = 0.75f
    )
}