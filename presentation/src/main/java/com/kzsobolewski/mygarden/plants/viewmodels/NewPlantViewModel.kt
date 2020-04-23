package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPlantViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val plantSaved = MutableLiveData<Boolean>(false)

    fun addNewPlantToFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePlant(createPlant())
            plantSaved.postValue(true)
        }
    }

    private fun createPlant(): Plant {
        return Plant(
            name = name.value ?: "",
            description = description.value ?: ""
        )
    }
}