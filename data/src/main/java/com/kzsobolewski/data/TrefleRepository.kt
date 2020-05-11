package com.kzsobolewski.data

import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.domain.models.TreflePlant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//again, interface not needed
class TrefleRepository : ITrefleRepository/*, ITrefleApi*/ {
    // this is pretty much the same implementation as in Firebase repository, so maybe we could extract in and provide by DI ?
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
        .baseUrl(BuildConfig.API_TREFLE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: ITrefleApi = retrofit.create(ITrefleApi::class.java)

    override suspend fun getPlantDetails(id: Int): TrefleDetailedPlant {
        return service.getPlantDetails(id)
    }

    override suspend fun getPlants(searchedPlant: String): List<TreflePlant> {
        return service.getPlants(searchedPlant)
    }
}