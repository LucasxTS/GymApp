package com.example.gymapp.ui.components.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gymapp.R
import com.example.gymapp.domain.models.Exercise
import java.net.URL


@Composable
fun ExerciseList(exercises: List<Exercise>) {
    Column {
        exercises.forEach { exercise ->
            ExerciseItem(exercise = exercise)
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png",
                placeholder = painterResource(id = R.drawable.baseline_autorenew_24),
                error = painterResource(id = R.drawable.baseline_autorenew_24),
                contentDescription = "The delasign logo",
                modifier = Modifier
                    .size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = exercise.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = exercise.observation,
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}

val item = Exercise(
    "Stiff com barra",
    URL("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png"),
    "12 Repetições"
)
@Preview
@Composable
fun ExerciseItemPreview() {
    ExerciseItem(item)
}


