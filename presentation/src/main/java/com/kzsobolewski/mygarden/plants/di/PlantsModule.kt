package com.kzsobolewski.mygarden.plants.di

import com.kzsobolewski.mygarden.plants.viewmodels.NewPlantViewModel
import com.kzsobolewski.mygarden.plants.viewmodels.PlantInfoViewModel
import com.kzsobolewski.mygarden.plants.viewmodels.PlantsViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val plantsModule = module {
    viewModel { PlantsViewModel(get()) }
    viewModel { NewPlantViewModel(get(), it.get<CoroutineScope>() as CoroutineScope) }
    viewModel { PlantInfoViewModel(get()) }
}