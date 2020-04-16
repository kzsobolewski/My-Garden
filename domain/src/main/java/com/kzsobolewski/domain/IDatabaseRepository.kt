package com.kzsobolewski.domain

interface IDatabaseRepository {

    suspend fun savePlant(plant: Plant)

    suspend fun getPlants(): PlantsResponse
}