package com.example.explora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerColumn()
        }
    }
}

@Preview
@Composable
fun AppPreview(){
    MiPrimerColumn()
}
@Composable
fun MiPrimerColumn(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
       Text("hello you",
           color = Color.Red,
           fontSize = 24.sp,
           fontStyle = FontStyle.Italic,
           fontWeight = FontWeight.ExtraBold,
           modifier = Modifier.padding(bottom = 8.dp)
           )
        Text("I'm red john",
            color = Color.Red,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(R.drawable.rj_face),
            contentDescription = "Foto cara red John",
            modifier = Modifier
                .height(130.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}