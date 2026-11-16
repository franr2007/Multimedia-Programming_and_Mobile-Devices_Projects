package com.example.practicaformularioscontroles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicaformularioscontroles.ui.screens.GestorActividadesScreen
import com.example.practicaformularioscontroles.ui.theme.PracticaFormulariosControlesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaFormulariosControlesTheme {
                GestorActividadesScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPreview() {
    GestorActividadesScreen()
}