package com.kzsobolewski.mygarden.search.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TreflePlant
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: ITrefleRepository) : ViewModel() {

    private val _plants = MutableLiveData<List<TreflePlant>>()

    val plants: MutableLiveData<List<TreflePlant>> = _plants

    fun loadPlants(searchedPlant: String) {
        viewModelScope.launch {
            try {
                val data = repository.getPlants(searchedPlant)
                _plants.postValue(data)
            } catch (e: Exception) {
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
            }
        }
    }
}