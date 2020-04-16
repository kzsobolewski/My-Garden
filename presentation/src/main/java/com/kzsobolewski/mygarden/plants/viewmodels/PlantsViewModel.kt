package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers

class PlantsViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    val plants: LiveData<Plant> = liveData(Dispatchers.IO) {
        val plants = repository.getPlants()
        emit(plants)
    }
}