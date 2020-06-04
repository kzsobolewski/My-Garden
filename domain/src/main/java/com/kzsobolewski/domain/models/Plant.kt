package com.kzsobolewski.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plant(
    val id: String = "",
    val name: String = "default Plant",
    val description: String = "...",
    val thumbnail: String?,
    val trefle_plant: TrefleDetailedPlant? = null,
    val hydration: Int = 0
//    val time: Date = GregorianCalendar.getInstance().time
) : Parcelable

typealias PlantsResponse = Map<String, Plant>
