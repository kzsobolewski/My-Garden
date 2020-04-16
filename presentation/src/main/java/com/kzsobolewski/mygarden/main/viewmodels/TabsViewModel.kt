package com.kzsobolewski.mygarden.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.Plant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TabsViewModel : ViewModel() {

    val repository = FirebaseRepository()

    fun createNewEntry() {
        Log.d("DEBUG", " Fab clicked")
        GlobalScope.launch(Dispatchers.IO) {
            repository.savePlant(Plant("FAB"))
        }
    }
}