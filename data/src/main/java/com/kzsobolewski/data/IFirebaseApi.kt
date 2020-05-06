package com.kzsobolewski.data

import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse
import retrofit2.http.*

interface IFirebaseApi {

    @GET("/plants.json")
    suspend fun getPlants(): PlantsResponse

    @POST("/plants.json")
    suspend fun savePlant(@Body plant: Plant)

    @DELETE("/plants/{id}.json")
    suspend fun deletePlant(@Path("id") id: String)
}