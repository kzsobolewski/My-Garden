package com.kzsobolewski.mygarden.di

import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { TabsViewModel() }
}