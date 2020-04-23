package com.kzsobolewski.mygarden.plants.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewPlantViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    var plantToAdd: Plant = Plant(name = "Data binding plant")

    fun addNewPlantToFirebase(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.savePlant(plantToAdd)
        }
    }
}