package com.kzsobolewski.domain

import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse

interface IDatabaseRepository {

    suspend fun savePlant(plant: Plant)

    suspend fun getPlants(): PlantsResponse

    suspend fun deletePlant(id: String)
}