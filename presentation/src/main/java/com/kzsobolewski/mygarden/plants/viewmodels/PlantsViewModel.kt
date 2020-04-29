package com.kzsobolewski.mygarden.plants.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.PlantsResponse
import kotlinx.coroutines.launch

class PlantsViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    private val unformattedPlants = MutableLiveData<PlantsResponse>()

    val plants: LiveData<List<Plant>> =
        unformattedPlants.map { apiResponse: PlantsResponse /* = Map<String,Plant>*/ ->

            val result = apiResponse.map { entity: Map.Entry<String, Plant> ->
                entity.value.copy(id = entity.key)
            }

            return@map result

            //apiResponse.values.sortedByDescending{ it.name} ?: listOf() /*value.values.sortedByDescending { it.time }*/
        }

    /*// TODO Callback instead of deffered return value
    fun loadPlantsAsync(): Deferred<Boolean> {
        return viewModelScope.async {
            try {
                val data = repository.getPlants()
                unformattedPlants.postValue(data)
                return@async true
            } catch (e: Exception) {
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
                return@async false
            }
        }
    }*/

    // TODO Callback instead of deffered return value
    fun loadPlantsAsync(callback: (value: Boolean) -> Unit) {
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
// java.text.ParseException: Failed to parse date ["Apr 29, 2020 13:49:02"]: Invalid number: Apr
}