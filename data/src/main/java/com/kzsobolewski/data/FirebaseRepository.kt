package com.kzsobolewski.data

import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.PlantsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseRepository : IDatabaseRepository {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.BUILD_TYPE == "debug")
            setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: IFirebaseApi = retrofit.create(IFirebaseApi::class.java)

    override suspend fun savePlant(plant: Plant) {
        service.savePlant(plant)
    }

    override suspend fun getPlants(): PlantsResponse {
        return service.getPlants()
    }
}