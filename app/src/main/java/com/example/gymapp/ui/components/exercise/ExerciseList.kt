package com.example.gymapp.ui.components.exercise

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
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
import com.example.gymapp.domain.models.Training
import java.net.URL


@Composable
fun ExerciseList(exercises: List<Exercise?>, onDelete: (Exercise) -> Unit, onEdit: (Exercise) -> Unit) {
    Column {
        exercises.forEach { exercise ->
            if (exercise != null) {
                ExerciseItem(exercise = exercise, onDelete, onEdit)
            }
        }
    }
}

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onDelete: (Exercise) -> Unit,
    onEdit: (Exercise) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = exercise.image,
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
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onEdit(exercise) }) {
                Image(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
            }
            IconButton(onClick = { onDelete(exercise) }) {
                Image(imageVector = Icons.Default.Delete, contentDescription = "Delete icon")
            }
        }
    }
}


@Preview
@Composable
fun ExerciseItemPreview() {
    ExerciseItem(exercise = Exercise("", "", ""), {}, {})
}


