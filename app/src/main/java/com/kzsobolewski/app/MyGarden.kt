package com.kzsobolewski.app

import android.app.Application
import com.kzsobolewski.mygarden.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyGarden : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyGarden)
            modules(appModule, presentationModule)
        }
    }
}