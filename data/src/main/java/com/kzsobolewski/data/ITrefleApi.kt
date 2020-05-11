package com.kzsobolewski.data

import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.domain.models.TreflePlant
import retrofit2.http.*

//this is secret, so you definitely don't want to check it in your github repository!
const val superSecretToken = "NHZWdHV2UXl3Y3Fsdk0xTUQ3eVJvdz09"
const val tokenQuery = "?token=$superSecretToken"

interface ITrefleApi {

    @Headers("Authorization: Bearer $superSecretToken")
    @GET("/api/plants$tokenQuery")
    suspend fun getPlants(@Query("q") searchedPlant: String): List<TreflePlant>

    @Headers("Authorization: Bearer $superSecretToken")
    @GET("/api/plants/{id}")
    suspend fun getPlantDetails(@Path("id") id: Int): TrefleDetailedPlant
}
