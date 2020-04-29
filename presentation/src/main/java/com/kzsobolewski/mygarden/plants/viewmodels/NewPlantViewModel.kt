package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPlantViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val hydration = MutableLiveData<Int>()

    val isPlantSaved = MutableLiveData<Boolean>(false)

    fun addNewPlantToFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePlant(createPlant())
            isPlantSaved.postValue(true)
        }
    }

    private fun createPlant(): Plant {
        return Plant(
            name = name.value ?: "",
            description = description.value ?: "",
            hydration = hydration.value ?: 0
        )
    }
}