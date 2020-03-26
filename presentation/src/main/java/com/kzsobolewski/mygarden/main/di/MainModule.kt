package com.kzsobolewski.mygarden.main.di

import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { TabsViewModel(get()) }
}