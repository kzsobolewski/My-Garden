package com.kzsobolewski.mygarden.plants.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import kotlinx.coroutines.launch

class PlantInfoViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    lateinit var currentPlant: Plant
    val isFromTrefle = MutableLiveData(false)
    val isDeleted = MutableLiveData(false)
    fun getPlantName() = currentPlant.name

    fun loadPlant(plant: Plant) {
        currentPlant = plant
        if (plant.trefle_plant != null)
            isFromTrefle.value = true
    }

    fun deleteCurrentPlant() {
        viewModelScope.launch {
            repository.deletePlant(currentPlant.id)
            try {
                val ref =
                    FirebaseStorage.getInstance().getReferenceFromUrl(currentPlant.thumbnail!!)
                ref.delete()
                    .addOnFailureListener {
                        Log.e(PlantInfoViewModel::class.simpleName, it.localizedMessage, it)
                    }
            } catch (e: Exception) {
                Log.e(PlantInfoViewModel::class.simpleName, e.localizedMessage, e)
            } finally {
                isDeleted.postValue(true)
            }
        }
    }
}
