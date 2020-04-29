package com.kzsobolewski.data

import com.google.gson.GsonBuilder
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

    private val gson = GsonBuilder()
        .setDateFormat("EEE dd, yyyy HH:mm:ss")
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    private val service: IFirebaseApi = retrofit.create(IFirebaseApi::class.java)

    override suspend fun savePlant(plant: Plant) {
        service.savePlant(plant)
    }

    override suspend fun getPlants(): PlantsResponse {
        return service.getPlants()
    }

    override suspend fun deletePlant(id: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}