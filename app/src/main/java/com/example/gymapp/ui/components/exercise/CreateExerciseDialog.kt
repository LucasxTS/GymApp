package com.example.gymapp.ui.components.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddExerciseDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (String, String, String) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Adicionar Exercício") },
        text = {
            Column {
                BasicTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (name.value.isEmpty()) {
                            Text(text = "Nome")
                        }
                        innerTextField()
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (description.value.isEmpty()) {
                            Text(text = "Descrição")
                        }
                        innerTextField()
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                BasicTextField(
                    value = imageUrl.value,
                    onValueChange = { imageUrl.value = it },
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (imageUrl.value.isEmpty()) {
                            Text(text = "URL da Imagem")
                        }
                        innerTextField()
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(name.value, description.value, imageUrl.value)
                    onDismissRequest()
                }
            ) {
                Text(text = "Adicionar")
            }
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Preview
@Composable
fun AlertDialogPreview() {
    AddExerciseDialog(onDismissRequest = { /*TODO*/ }, onConfirm = { a, b, c ->

    })

}