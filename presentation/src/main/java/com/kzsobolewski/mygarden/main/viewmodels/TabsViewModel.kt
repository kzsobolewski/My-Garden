package com.kzsobolewski.mygarden.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kzsobolewski.domain.repository.IPlantsRepository

class TabsViewModel(val repository: IPlantsRepository) : ViewModel() {

    var value = 5

    fun createNewEntry() {
        Log.d("DEBUG", " Fab clicked")
    }

    fun isValueSmall(): Boolean{
        return value < 10
    }

}