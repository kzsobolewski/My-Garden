package com.kzsobolewski.mygarden.search.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class TreflePlantDetailedInfoViewModel(private val repository: ITrefleRepository) : ViewModel() {

    lateinit var commonName: String

    private val _plantDetails = MutableLiveData<TrefleDetailedPlant>()
    // tu trzeba zmienic typ na LiveData<> - inaczej druga zmienna nie ma sensu
    val plantDetails: LiveData<TrefleDetailedPlant> = _plantDetails

    val isLoading = MutableLiveData<Boolean>(false)

    fun loadData(id: Int){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val data = repository.getPlantDetails(id)
                _plantDetails.postValue(data)
            } catch (e: Exception){
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
            }

            isLoading.value = false
        }
    }

}