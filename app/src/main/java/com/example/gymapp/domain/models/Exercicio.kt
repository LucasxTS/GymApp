package com.example.gymapp.domain.models

import android.net.Uri

data class Exercise(
    val name: String,
    val image: Uri,
    val observation: String
)
