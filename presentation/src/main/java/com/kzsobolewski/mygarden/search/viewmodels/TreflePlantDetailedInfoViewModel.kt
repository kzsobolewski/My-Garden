package com.kzsobolewski.mygarden.search.viewmodels

import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.kzsobolewski.domain.ITrefleRepository
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.coroutines.launch

class TreflePlantDetailedInfoViewModel(private val repository: ITrefleRepository) : ViewModel() {

    private val _plantDetails = MutableLiveData<TrefleDetailedPlant>()
    val isLoading = MutableLiveData<Boolean>(false)
    val plantDetails: LiveData<TrefleDetailedPlant> = _plantDetails

    fun loadData(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val data = repository.getPlantDetails(id)
                _plantDetails.postValue(data)
            } catch (e: Exception) {
                Log.e(PlantsViewModel::class.simpleName, e.localizedMessage, e)
            }
            isLoading.value = false
        }
    }

    fun createNewPlant(view: View){
        val bundle = bundleOf("TREFLE_KEY" to plantDetails.value)
        Navigation.findNavController(view).navigate(R.id.action_treflePlantDetailedInfoFragment_to_newPlantFragment, bundle)
    }
}