package com.kzsobolewski.mygarden.plants.viewmodels

import androidx.lifecycle.*
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.PlantsResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PlantsViewModel(private val repository: FirebaseRepository) : ViewModel() {

    private val unformattedPlants = MutableLiveData<PlantsResponse>()

    val plants: LiveData<List<Plant>> =
        unformattedPlants.map { value -> value.values.sortedByDescending { it.time } }

    // TODO Callback instead of deffered return value
    fun loadPlantsAsync(): Deferred<Boolean> {
        return viewModelScope.async {
            try {
                val data = repository.getPlants()
                unformattedPlants.postValue(data)
                return@async true
            } catch (e: Exception) {
                return@async false
            }
        }
    }

}