package com.kzsobolewski.mygarden.plants.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.PlantsResponse
import kotlinx.coroutines.launch

class PlantsViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    private val unformattedPlants = MutableLiveData<PlantsResponse>()

    val plants: LiveData<List<Plant>> =
        unformattedPlants.map { apiResponse ->
            apiResponse.map { entity ->
                entity.value.copy(id = entity.key)
            }
        }


    fun loadPlants(callback: (value: Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val data = repository.getPlants()
                unformattedPlants.postValue(data)
                callback.invoke(true)
            } catch (e: Exception) {
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
                callback.invoke(false)
            }
        }
    }

}