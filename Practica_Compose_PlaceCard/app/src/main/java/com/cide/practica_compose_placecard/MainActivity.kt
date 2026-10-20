package com.cide.practica_compose_placecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cide.practica_compose_placecard.ui.theme.Practica_Compose_PlaceCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_Compose_PlaceCardTheme {
                PlaceCard()
            }
        }
    }
}

@Composable
fun PlaceCard(){
    PlaceImage()
    PlaceDescription()
    PlaceActions()
}

@Composable
fun PlaceImage () {
    Image(
        painter = painterResource(R.drawable.sierratramuntana),
        contentDescription = "Fotografia de la sierra de tramuntana",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(23.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PlaceDescription(){

}

@Composable
fun PlaceActions (){

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Practica_Compose_PlaceCardTheme {
    PlaceCard()
    }
}