package com.kzsobolewski.data

import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.domain.models.TreflePlant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val superSecretToken = "NHZWdHV2UXl3Y3Fsdk0xTUQ3eVJvdz09"
const val tokenQuery = "?token=$superSecretToken"

interface ITrefleApi {

    @GET("/api/plants$tokenQuery")
    suspend fun getPlants(@Query("q") searchedPlant: String): List<TreflePlant>

    @GET("/api/plants/{id}$tokenQuery")
    suspend fun getPlantDetails(@Path("id") id: Int): TrefleDetailedPlant
}
