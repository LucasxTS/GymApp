package com.example.gymapp.domain.models

import android.net.Uri
import java.net.URL

data class Exercise(
    val name: String,
    val image: URL,
    val observation: String
)
