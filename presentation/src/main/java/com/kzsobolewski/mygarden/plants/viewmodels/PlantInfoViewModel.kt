package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.IDatabaseRepository
import kotlinx.coroutines.launch

class PlantInfoViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    fun deletePlant(id: String) {
        viewModelScope.launch {
            repository.deletePlant(id)
        }
    }
}
