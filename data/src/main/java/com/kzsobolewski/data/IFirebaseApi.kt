package com.kzsobolewski.data

import com.kzsobolewski.domain.Plant
import retrofit2.http.GET
import retrofit2.http.POST

interface IFirebaseApi {

    @GET("/plants/M123.json")
    suspend fun getPlants(): Plant

    @POST("/plants.json")
    suspend fun savePlant(plant: Plant)
}