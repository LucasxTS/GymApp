package com.example.gymapp.ui.components.home

import android.annotation.SuppressLint
import android.media.Image
import android.os.Build
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.domain.models.Training
import com.example.gymapp.ui.features.home.HomeViewModel
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat

@Composable
fun TrainingList(trainingList: List<Training>, viewModel: HomeViewModel) {
    LazyColumn {
        itemsIndexed(trainingList) { index, item ->
            TrainingCard(item, viewModel)
        }
    }
}

@Composable
fun TrainingCard(training: Training, viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color(0xFF9AECDB), shape = RoundedCornerShape(8.dp))
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = training.name,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                   Image(imageVector = Icons.Default.Edit, contentDescription = "Edit Icon")
                }
                IconButton(onClick = { /*TODO*/ }) {
                   Image(imageVector = Icons.Default.Delete, contentDescription = "Delete icon")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = training.description,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 12.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = formatTimestamp(training.date),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                )
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatTimestamp(timestamp: Timestamp): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    return sdf.format(timestamp.toDate())
}


@RequiresApi(Build.VERSION_CODES.O)
val training = listOf<Training>(
    Training("Peito", "Segunda e sexta", Timestamp.now()),
    Training("Biceps", "terça", Timestamp.now()),
    Training("Triceps", "quarta", Timestamp.now()),
    Training("Ombro", "quinta", Timestamp.now()),
    Training("Perna", "sabado", Timestamp.now()),
    Training("Posterior de coxa", "nunca", Timestamp.now())
)

val aaa = Training("Peito", "Segunda e sexta", Timestamp.now())

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TrainingListPreview() {

}