package com.kzsobolewski.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.FirebaseApp
import com.kzsobolewski.domain.Plant
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirebaseDBTest {
    @Test
    fun writingToDB() {
        FirebaseApp.initializeApp(InstrumentationRegistry.getInstrumentation().targetContext)
        val plant = Plant("test plant")
        FireBaseDatabaseApi().writeToDB(plant)
    }

    @Test
    fun readFromDB() {
        FirebaseApp.initializeApp(InstrumentationRegistry.getInstrumentation().targetContext)
        FireBaseDatabaseApi().readFromDB()
    }
}