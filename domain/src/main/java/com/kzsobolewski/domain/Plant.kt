package com.kzsobolewski.domain

data class Plant(
    val name: String,
    val description: String = "...",
    val thumbnail: String = "",
    val hydration: Float = 1f
)