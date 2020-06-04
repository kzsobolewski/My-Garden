package com.kzsobolewski.domain

import android.net.Uri
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse

interface IDatabaseRepository {

    suspend fun savePlant(plant: Plant)

    suspend fun getPlants(): PlantsResponse

    suspend fun deletePlant(id: String)

    suspend fun addImage(uri: Uri): String

    suspend fun deleteImage(url: String)
}