package com.kzsobolewski.app

import com.kzsobolewski.data.di.repositoryModule
import com.kzsobolewski.mygarden.di.presentationModules
import org.koin.core.module.Module

val appModules = mutableListOf<Module>().apply {
    addAll(presentationModules)
    add(repositoryModule)
}


