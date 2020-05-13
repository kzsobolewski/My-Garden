package com.kzsobolewski.domain.models

data class TreflePlant(
    val common_name: String,
    val complete_data: Boolean = false,
    val id: Int = 0,
    val link: String = "",
    val scientific_name: String = "",
    val slug: String = ""
)