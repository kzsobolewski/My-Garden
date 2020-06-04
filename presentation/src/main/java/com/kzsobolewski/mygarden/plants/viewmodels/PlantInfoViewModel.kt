package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import kotlinx.coroutines.launch

class PlantInfoViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    lateinit var currentPlant: Plant
    val plantName: String
        get() = currentPlant.name

    fun loadPlant(plant: Plant) {
        currentPlant = plant
    }

    fun deleteCurrentPlant(callback: (deleted: Boolean?) -> Unit) {
        viewModelScope.launch {
            currentPlant.thumbnail?.let {
                repository.deleteImage(it)
            }
            repository.deletePlant(currentPlant.id)
            callback.invoke(true)
        }
    }
}
