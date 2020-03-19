package com.kzsobolewski.app

import com.kzsobolewski.domain.Plant
import org.koin.dsl.module

val appModule = module {

    factory { Plant("plant1", "...") }

}


