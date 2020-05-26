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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NewPlantViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    var trefleDetails: TrefleDetailedPlant? = null
    private var imageUrl: String? = null
    val isPlantSaved = MutableLiveData(false)

    fun addNewPlantToFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePlant(createPlant())
            isPlantSaved.postValue(true)
        }
    }

    fun uploadImageToFirebase(uri: Uri) {
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/img/$filename")

        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                imageUrl = it.toString()
            }
        }.addOnFailureListener {
            Log.e(NewPlantViewModel::class.simpleName, it.localizedMessage, it)
        }
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