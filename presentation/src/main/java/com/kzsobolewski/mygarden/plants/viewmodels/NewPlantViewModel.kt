package com.kzsobolewski.mygarden.plants.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.models.Plant
import com.kzsobolewski.domain.models.TrefleDetailedPlant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPlantViewModel(private val repository: IDatabaseRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    var trefleDetails: TrefleDetailedPlant? = null
    private var imageUrl: String? = null
    val isPlantSaved = MutableLiveData(false)
    var imageUri: Uri? = null
    val isLoading = MutableLiveData<Boolean>(false)


    fun addNewPlantToFirebase(uri: Uri?) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            uri?.let {
                imageUrl = repository.addImage(it)
            }
            repository.savePlant(createPlant())
            isPlantSaved.postValue(true)
        }
    }


    fun fillTheName() {
        name.value = trefleDetails!!.scientific_name
    }

    private fun createPlant(): Plant {
        return Plant(
            name = name.value ?: "",
            description = description.value ?: "",
            trefle_plant = trefleDetails,
            thumbnail = imageUrl
        )
    }
}