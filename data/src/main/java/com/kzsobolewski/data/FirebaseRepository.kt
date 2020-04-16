package com.kzsobolewski.data

import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.Plant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseRepository : IDatabaseRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://my-garden-ea5bb.firebaseio.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: IDatabaseRepository = retrofit.create(IDatabaseRepository::class.java)

    override suspend fun savePlant(plant: Plant) {
        service.savePlant(plant)
    }

    override suspend fun getPlants(): Plant {
        return service.getPlants()
    }
}