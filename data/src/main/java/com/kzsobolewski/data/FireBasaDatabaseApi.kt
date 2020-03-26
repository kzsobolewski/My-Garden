package com.kzsobolewski.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kzsobolewski.domain.Plant
import com.kzsobolewski.domain.repository.IPlantsRepository

class FireBaseDatabaseApi: IPlantsRepository {

    private val reference = Firebase.database.getReference("plants")

    override fun readPlant(): LiveData<Plant> {
        val plantLiveData = MutableLiveData<Plant>()

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val plant = p0.getValue(Plant::class.java) ?: return
                Log.d("debugFirebase", plant.toString())
                plantLiveData.postValue(plant)
            }

            override fun onCancelled(p0: DatabaseError) {}
        })

        return plantLiveData
    }

    override fun writePlant(plant: Plant) {
        reference.push().setValue(plant).addOnSuccessListener {
            Log.d("debugFirebase", "sending complete")
        }.addOnFailureListener {
            Log.d("debugFirebase", "sending failed")
        }
    }
}