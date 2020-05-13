package com.kzsobolewski.mygarden.search.di

import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import com.kzsobolewski.mygarden.search.viewmodels.TreflePlantDetailedInfoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single { SearchViewModel(get()) }
    viewModel { TreflePlantDetailedInfoViewModel(get()) }
}