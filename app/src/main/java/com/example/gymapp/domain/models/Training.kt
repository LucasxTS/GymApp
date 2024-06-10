package com.example.gymapp.domain.models

import com.google.firebase.Timestamp

data class Training(
    val name: String,
    val description: String,
    val date: Timestamp = Timestamp.now()
) {
    constructor() : this("", "", Timestamp.now())
}
