package com.example.gymapp.domain.models

import android.net.Uri
import com.google.firebase.Timestamp
import java.net.URL

data class Exercise(
    val name: String = "",
    val image: String = "",
    val observation: String = ""
)