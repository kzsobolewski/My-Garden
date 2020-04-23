package com.kzsobolewski.mygarden.plants.di

import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val plantsModule = module {
    viewModel { PlantsViewModel() }
    viewModel { NewPlantViewModel() }
}