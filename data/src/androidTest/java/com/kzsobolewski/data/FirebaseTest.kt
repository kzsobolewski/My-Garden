package com.kzsobolewski.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kzsobolewski.domain.models.Plant
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirebaseTest {
    @Test
    fun writingToDB() {
        val plant = Plant("test plant")
    }

    @Test
    fun readFromDB() {
    }
}