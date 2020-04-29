package com.kzsobolewski.data.di

import com.kzsobolewski.data.FirebaseRepository
import com.kzsobolewski.domain.IDatabaseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IDatabaseRepository>{ FirebaseRepository() }
}