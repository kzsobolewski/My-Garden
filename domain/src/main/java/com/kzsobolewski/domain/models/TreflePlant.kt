package com.kzsobolewski.domain.models

data class TreflePlant(
    val common_name: String,
    val complete_data: Boolean,
    val id: Int,
    val link: String,
    val scientific_name: String,
    val slug: String
)