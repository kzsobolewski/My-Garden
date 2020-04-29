package com.kzsobolewski.mygarden.plants.di

import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import com.kzsobolewski.mygarden.plants.viewmodels.PlantInfoViewModel
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val plantsModule = module {
    viewModel { PlantsViewModel(get()) }
    viewModel { NewPlantViewModel(get()) }
    viewModel { PlantInfoViewModel() }
    single { FirebaseRepository() }
}