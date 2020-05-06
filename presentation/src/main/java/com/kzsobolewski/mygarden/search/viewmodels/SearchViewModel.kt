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

    val plants = MutableLiveData<List<TreflePlant>>()

    fun loadPlants() {
        viewModelScope.launch {
            try {
                val data = repository.getPlants("green")
                plants.postValue(data)
            } catch (e: Exception) {
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
            }
        }
    }
}