package com.cide.practica_compose_placecard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(

    // Texto normal y descripciones
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),

    // Títulos
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),

    titleSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),

    // Encabezados principales
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),

    headlineMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),

    headlineSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),

    // Etiquetas y botones
    labelLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),

    labelMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    )
)