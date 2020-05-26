package com.kzsobolewski.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plant(
    val id: String = "",
    val name: String = "default Plant",
    val description: String = "...",
    val thumbnail: String? = "https://firebasestorage.googleapis.com/v0/b/my-garden-ea5bb.appspot.com/o/default.jpeg?alt=media&token=dcbf85c1-1cd0-4f57-ba23-6040aa7055cc",
    val trefle_plant: TrefleDetailedPlant? = null,
    val hydration: Int = 0
//    val time: Date = GregorianCalendar.getInstance().time
) : Parcelable

typealias PlantsResponse = Map<String, Plant>
