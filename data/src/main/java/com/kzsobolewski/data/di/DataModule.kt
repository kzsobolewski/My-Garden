package com.kzsobolewski.data.di

import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.data.TrefleRepository
import com.kzsobolewski.domain.IDatabaseRepository
import com.kzsobolewski.domain.ITrefleRepository
import org.koin.dsl.module

val dataModule = module {
    single<IDatabaseRepository> { FirebaseRepository() }
    single<ITrefleRepository> { TrefleRepository() }
}