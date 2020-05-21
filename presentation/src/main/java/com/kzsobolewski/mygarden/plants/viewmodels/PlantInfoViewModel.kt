package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import kotlinx.coroutines.launch

class PlantInfoViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    var currentPlant: Plant? = null
    val isFromTrefle = MutableLiveData<Boolean>(false)
    val plantName = currentPlant?.name

    fun loadPlant(plant: Plant) {
        currentPlant = plant
        if (plant.trefle_plant != null)
            isFromTrefle.value = true
    }

    fun deleteCurrentPlant() {
        if (currentPlant?.id != null) {
            viewModelScope.launch {
                repository.deletePlant(currentPlant?.id!!)
            }
        }
    }
}
