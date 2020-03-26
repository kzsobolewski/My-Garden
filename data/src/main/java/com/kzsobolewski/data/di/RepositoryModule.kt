package com.kzsobolewski.data.di

import com.kzsobolewski.data.FireBaseDatabaseApi
import com.kzsobolewski.domain.repository.IPlantsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPlantsRepository> { FireBaseDatabaseApi() }
}