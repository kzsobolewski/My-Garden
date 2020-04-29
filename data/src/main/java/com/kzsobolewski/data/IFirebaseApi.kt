package com.kzsobolewski.data

import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.PlantsResponse
import retrofit2.http.*

interface IFirebaseApi {

    @GET("/plants.json")
    suspend fun getPlants(): PlantsResponse

    @POST("/plants.json")
    suspend fun savePlant(@Body plant: Plant)

    @DELETE("/plants.json")
    suspend fun deletePlant(@Body id: String) : String
}