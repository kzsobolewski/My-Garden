package com.kzsobolewski.mygarden.plants.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NewPlantViewModel(private val repository: IDatabaseRepository, private val activityScope: CoroutineScope) : ViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    var trefleDetails: TrefleDetailedPlant? = null
    private var imageUrl: String? = null
    val isPlantSaved = MutableLiveData(false)

    fun addNewPlantToFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            //zapisywanie obrazka do bazy powinno byc raczej robione tutaj
            repository.savePlant(createPlant())
            isPlantSaved.postValue(true)
        }
    }

    fun uploadImageToFirebase(uri: Uri) {
        val filename = UUID.randomUUID().toString()
        // dobry przykład na użycie use case :)
        // przykład jak przekształcic podejście z listenerami na korutyny
        viewModelScope.launch {
            imageUrl = uploadImage(uri)
        }


        /*val ref = FirebaseStorage.getInstance().getReference("/img/$filename")

        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                imageUrl = it.toString()
            }
        }.addOnFailureListener {
            Log.e(NewPlantViewModel::class.simpleName, it.localizedMessage, it)
        }*/
    }

    private suspend fun uploadImage(uri: Uri): String {
        return suspendCoroutine { continuation ->
            val filename = UUID.randomUUID().toString()

            //Generalnie nie chcemy miec firebase storage w viewModelu - powinno to byc schowane za abstrakcja - np. naszym IDatabaseRepository
            val ref = FirebaseStorage.getInstance().getReference("/img/$filename")

            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    continuation.resume(it.toString())
                }
            }.addOnFailureListener {
                Log.e(NewPlantViewModel::class.simpleName, it.localizedMessage, it)

                continuation.resumeWithException(it)
            }
        }
    }

    //lub rx!

    private fun uploadImageRx(uri: Uri){
        Single.create<String> { emitter ->
            val filename = UUID.randomUUID().toString()

            //Generalnie nie chcemy miec firebase storage w viewModelu - powinno to byc schowane za abstrakcja - naszym IDatabaseRepository
            val ref = FirebaseStorage.getInstance().getReference("/img/$filename")

            ref.putFile(uri).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    emitter.onSuccess(it.toString())
                }
            }.addOnFailureListener {
                Log.e(NewPlantViewModel::class.simpleName, it.localizedMessage, it)

                emitter.onError(it)
            }
        }
            .subscribe(
                {result -> imageUrl = result},
                {}
            )
    }

    fun fillTheName() {
        name.value = trefleDetails!!.scientific_name
    }

    private fun createPlant(): Plant {
        return Plant(
            name = name.value ?: "",
            description = description.value ?: "",
            trefle_plant = trefleDetails,
            thumbnail = imageUrl
        )
    }

}