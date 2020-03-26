package com.kzsobolewski.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kzsobolewski.domain.Plant

class FireBaseDatabaseApi {

    private val reference = Firebase.database.getReference("plants")

    fun writeToDB(plant: Plant) {
        reference.push().setValue(plant).addOnSuccessListener {
            Log.d("debugFirebase", "sending complete")
        }.addOnFailureListener {
            Log.d("debugFirebase", "sending failed")
        }
    }


    fun readFromDB() {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val plant = p0.getValue(Plant::class.java) ?: return
                Log.d("debugFirebase", plant.toString())
            }

            override fun onCancelled(p0: DatabaseError) {}
        })
    }


}