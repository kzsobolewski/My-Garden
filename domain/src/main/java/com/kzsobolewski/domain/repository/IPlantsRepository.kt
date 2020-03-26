package com.kzsobolewski.domain.repository

import androidx.lifecycle.LiveData
import com.kzsobolewski.domain.Plant

interface IPlantsRepository {
    fun readPlant(): LiveData<Plant>
    fun writePlant(plant: Plant)
}