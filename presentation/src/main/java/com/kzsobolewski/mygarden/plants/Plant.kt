package com.kzsobolewski.mygarden.plants

import android.graphics.Bitmap

data class Plant(
    val name: String,
    val description: String,
    val thumbnail: Int = -1,
    val hydration: Float = 1f
)

