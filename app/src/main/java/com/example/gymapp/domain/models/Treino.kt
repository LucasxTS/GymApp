package com.example.gymapp.domain.models

import java.time.LocalDateTime

data class Training(
    val name: String,
    val description: String,
    val date: LocalDateTime
)
