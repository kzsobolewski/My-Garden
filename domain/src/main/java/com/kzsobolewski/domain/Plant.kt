package com.kzsobolewski.domain

data class Plant(
    val name: String = "default Plant",
    val description: String = "...",
    val thumbnail: String = "",
    val hydration: Float = 1f
)

typealias PlantsResponse = Map<String, Plant>
