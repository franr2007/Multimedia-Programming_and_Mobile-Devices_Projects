package com.cide.practica_compose_placecard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cide.practica_compose_placecard.R

//Tipografia usada Nunito, archivos en  res/fonts
val Nunito = FontFamily(
    Font(R.font.nunito_bold, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)
val AppTypography = Typography(

    // Texto normal y descripciones
    bodyLarge = TextStyle(fontFamily = Nunito, fontSize = 20.sp, fontWeight = FontWeight.Normal),
    bodyMedium = TextStyle(fontFamily = Nunito, fontSize = 14.sp, fontWeight = FontWeight.Normal),
    bodySmall = TextStyle(fontFamily = Nunito, fontSize = 12.sp, fontWeight = FontWeight.Normal),

    // Títulos
    titleLarge = TextStyle(fontFamily = Nunito, fontSize = 24.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontFamily = Nunito, fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
    titleSmall = TextStyle(fontFamily = Nunito, fontSize = 16.sp, fontWeight = FontWeight.Medium),

    // Encabezados principales
    headlineLarge = TextStyle(fontFamily = Nunito, fontSize = 32.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontFamily = Nunito, fontSize = 28.sp, fontWeight = FontWeight.Bold),
    headlineSmall = TextStyle(fontFamily = Nunito, fontSize = 24.sp, fontWeight = FontWeight.Bold),

    // Etiquetas y botones
    labelLarge = TextStyle(fontFamily = Nunito, fontSize = 14.sp, fontWeight = FontWeight.Medium),
    labelMedium = TextStyle(fontFamily = Nunito, fontSize = 12.sp, fontWeight = FontWeight.Medium),
    labelSmall = TextStyle(fontFamily = Nunito, fontSize = 11.sp, fontWeight = FontWeight.Medium)
)