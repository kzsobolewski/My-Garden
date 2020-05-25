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
    val viewState = MutableLiveData(SearchViewState.Init)
    val plants: LiveData<List<TreflePlant>> = _plants

    fun loadPlants(searchedPlant: String?) {
        if (searchedPlant != null && searchedPlant.length > 2) {
            viewModelScope.launch {
                try {
                    requestPlants(searchedPlant)
                } catch (e: Exception) {
                    Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
                    viewState.postValue(SearchViewState.Error)
                }
            }
        }
    }

    private suspend fun requestPlants(searchedPlant: String) {
        viewState.postValue(SearchViewState.Loading)
        val data = repository.getPlants(searchedPlant)
        if (data.isNullOrEmpty()) {
            _plants.postValue(listOf())
            viewState.postValue(SearchViewState.NoResults)
        } else {
            _plants.postValue(data)
            viewState.postValue(SearchViewState.Content)
        }
    }
}
