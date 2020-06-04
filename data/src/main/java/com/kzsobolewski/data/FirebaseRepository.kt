package com.kzsobolewski.data

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.kzsobolewski.data.BuildConfig.API_BASE_URL
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseRepository : IDatabaseRepository, Repository(API_BASE_URL) {

    private val service: IFirebaseApi = retrofit.create(IFirebaseApi::class.java)
    private val firebaseStorage = FirebaseStorage.getInstance()

    override suspend fun savePlant(plant: Plant) {
        service.savePlant(plant)
    }

    override suspend fun getPlants(): PlantsResponse {
        return service.getPlants()
    }

    override suspend fun deletePlant(id: String) {
        service.deletePlant(id)
    }

    override suspend fun addImage(uri: Uri): String {
        return suspendCoroutine { continuation ->
            val filename = UUID.randomUUID().toString()
            val ref = firebaseStorage.getReference("/img/$filename")

            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    continuation.resume(it.toString())
                }
            }.addOnFailureListener {
                Log.e(FirebaseRepository::class.simpleName, it.localizedMessage, it)
                continuation.resumeWithException(it)
            }
        }
    }

    override suspend fun deleteImage(url: String) {
        val ref = firebaseStorage.getReferenceFromUrl(url)
        ref.delete().addOnFailureListener {
            Log.e(FirebaseRepository::class.simpleName, it.localizedMessage, it)
        }
    }
}