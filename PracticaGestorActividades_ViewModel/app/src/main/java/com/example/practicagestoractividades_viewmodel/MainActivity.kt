
package com.example.practicagestoractividades_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicagestoractividades_viewmodel.ui.screens.GestorActividadesRoute
import com.example.practicagestoractividades_viewmodel.ui.screens.GestorActividadesScreen
import com.example.practicagestoractividades_viewmodel.ui.theme.PracticaFormulariosControlesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PracticaFormulariosControlesTheme {
                GestorActividadesRoute()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPreview() {
    PracticaFormulariosControlesTheme {
        GestorActividadesScreen(
            uiState = com.example.practicagestoractividades_viewmodel.ui.ActividadesUiState(),
            onNombreChange = {},
            onDescripcionChange = {},
            onCategoriaChange = {},
            onDificultadChange = {},
            onMaterialIncluidoChange = {},
            onGratuitaChange = {},
            onPrecioChange = {},
            onGuardar = {},
            onEliminar = {}
        )
    }
}
