package com.example.gymapp.ui.components.home

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
           .padding(24.dp)
   ){
    Text(
        text = "How are you feeling today?",
        color = Color.White,
        fontSize = 48.sp,


    )
   }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    HomeHeader()
}