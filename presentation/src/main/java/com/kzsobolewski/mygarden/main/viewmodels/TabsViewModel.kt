package com.kzsobolewski.mygarden.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kzsobolewski.mygarden.main.fragments.TempUseCase

class TabsViewModel(private val useCase: TempUseCase, text: String, number: Double) : ViewModel() {

    fun createNewEntry() {
        Log.d("DEBUG", " Fab clicked")
    }


}