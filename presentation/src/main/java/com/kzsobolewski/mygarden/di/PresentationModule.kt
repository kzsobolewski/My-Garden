package com.kzsobolewski.mygarden.di

import com.kzsobolewski.mygarden.main.di.mainModule
import com.kzsobolewski.mygarden.plants.di.plantsModule
import com.kzsobolewski.mygarden.search.di.searchModule

val presentationModule = listOf(mainModule, plantsModule, searchModule)