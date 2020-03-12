package com.kzsobolewski.mygarden.main.viewmodels

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainViewModel : ViewModel() {

    fun fabClicked(view: View){
        Log.d("DEBUG"," Fab clicked" )
    }

}