package com.kzsobolewski.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyGarden : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            startKoin {
                androidLogger()
                androidContext(this@MyGarden)
                modules(appModule)
            }
        }
    }
}