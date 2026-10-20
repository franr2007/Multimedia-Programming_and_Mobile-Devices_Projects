package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme
import com.example.happybirthday.ui.theme.Pink80
import com.example.happybirthday.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingText(mensaje = "Happy Birthday Carla!", departe = "From Xisco", modificador = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun GreetingText(mensaje: String, departe: String, modificador: Modifier = Modifier){
    Column(
        modifier = modificador.background(Pink80),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = mensaje,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
        Text(
            text = departe,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End),
            color = Purple40
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreviewPreview() {
    HappyBirthdayTheme {
        GreetingText(mensaje = "Happy Birthday Carla!", departe = "From Xisco")
    }
}