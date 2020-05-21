package com.kzsobolewski.mygarden.search.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: ITrefleRepository) : ViewModel() {

    private val _plants = MutableLiveData<List<TreflePlant>>()
    val isLoading = MutableLiveData<Boolean>(false)
    val isEmpty = MutableLiveData<Boolean>(true)
    val isNoResults = MutableLiveData<Boolean>(false)

    val plants: LiveData<List<TreflePlant>> = _plants

    // TODO come up with better way to handle recyclerview states
    fun loadPlants(searchedPlant: String?) {
        isNoResults.value = false
        if (searchedPlant != null)
            viewModelScope.launch {
                isLoading.value = true
                try {
                    when {
                        searchedPlant.isEmpty() -> {
                            _plants.postValue(listOf())
                            isEmpty.value = true
                        }
                        searchedPlant.length > 2 -> {
                            requestPlants(searchedPlant)
                        }
                    }
                } catch (e: Exception) {
                    Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
                }
                isLoading.value = false
            }
    }

    private suspend fun requestPlants(searchedPlant: String) {
        isEmpty.value = false
        val data = repository.getPlants(searchedPlant)
        if (data.isNullOrEmpty()) {
            isNoResults.value = true
            _plants.postValue(listOf())
        } else {
            _plants.postValue(data)
            isNoResults.value = false
        }
    }
}