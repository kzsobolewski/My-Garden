package com.kzsobolewski.mygarden.di

import com.kzsobolewski.mygarden.main.fragments.TempUseCase
import com.kzsobolewski.mygarden.main.fragments.UseCase
import com.kzsobolewski.mygarden.main.viewmodels.TabsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TabsViewModel(useCase = get(), text = get(), number = get()) }
    factory { TempUseCase() }
    single<UseCase> { TempUseCase() }
}