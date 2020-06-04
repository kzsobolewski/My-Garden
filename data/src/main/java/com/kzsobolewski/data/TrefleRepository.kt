package com.kzsobolewski.data

import com.kzsobolewski.data.BuildConfig.API_TREFLE_URL
import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.domain.models.TreflePlant

class TrefleRepository : ITrefleRepository, Repository(API_TREFLE_URL) {


    private val service: ITrefleApi = retrofit.create(ITrefleApi::class.java)

    override suspend fun getPlantDetails(id: Int): TrefleDetailedPlant {
        return service.getPlantDetails(id)
    }

    override suspend fun getPlants(searchedPlant: String): List<TreflePlant> {
        return service.getPlants(searchedPlant)
    }
}