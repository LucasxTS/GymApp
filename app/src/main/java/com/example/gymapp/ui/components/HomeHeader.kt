package com.example.gymapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeHeader() {
    Column {

    }
   Row (
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement =  Arrangement.Center,
       modifier = Modifier
           .fillMaxWidth()
           .padding(16.dp)
   ){
    Text(
        text = "BE THE BEST OF YOU.",
        color = Color.Black,
        fontSize = 32.sp,
        textAlign = TextAlign.Center

    )
   }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}