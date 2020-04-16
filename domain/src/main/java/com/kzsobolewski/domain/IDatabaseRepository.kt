package com.kzsobolewski.domain

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IDatabaseRepository {

    @POST("/plants.json")
    suspend fun savePlant(@Body plant: Plant)

    @GET("/plants.json")
    suspend fun getPlants(): Plant
}