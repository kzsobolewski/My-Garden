package com.kzsobolewski.mygarden.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kzsobolewski.mygarden.R
import com.kzsobolewski.mygarden.databinding.ActivityMainBinding
import com.kzsobolewski.mygarden.main.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewmodel = MainViewModel()
    }

}