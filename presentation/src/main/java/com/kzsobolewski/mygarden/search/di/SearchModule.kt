package com.kzsobolewski.mygarden.search.di

import com.kzsobolewski.mygarden.search.viewmodels.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(get()) }
}