package com.kzsobolewski.mygarden.main.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TabsViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    fun goToNewPlantFragment() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.savePlant(Plant("FAB"))
        }
    }
}