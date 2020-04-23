package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.*
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.PlantsResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PlantsViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    private val _plants2 = MutableLiveData<PlantsResponse>()

    private val _plants: LiveData<PlantsResponse> = liveData(Dispatchers.IO) {
        val plants = repository.getPlants()
        emit(plants)
    }

    val plants: LiveData<List<Plant>> = _plants2.map { value -> value.values.sortedBy { it.name } }

    fun loadPlants(): Deferred<Boolean> {
        return viewModelScope.async {
            try {
                val plants = repository.getPlants()
                _plants2.postValue(plants)
                true
            } catch (e: Exception) {
                false
            }
        }

        /*viewModelScope.launch {
            val plants = repository.getPlants()
            _plants2.postValue(plants)

        }*/
    }
}