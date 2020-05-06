package com.kzsobolewski.domain

import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.domain.models.TreflePlant

interface ITrefleRepository {

    suspend fun getPlants(searchedPlant: String): List<TreflePlant>

    suspend fun getPlantDetails(id: Int): TrefleDetailedPlant
}