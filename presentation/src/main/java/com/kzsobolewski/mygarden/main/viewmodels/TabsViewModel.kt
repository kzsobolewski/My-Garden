package com.kzsobolewski.mygarden.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.*

class TabsViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    fun goToNewPlantFragment() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.savePlant(Plant("FAB"))
        }
    }
}