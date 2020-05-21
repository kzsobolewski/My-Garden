package com.kzsobolewski.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plant(
    val id: String = "",
    val name: String = "default Plant",
    val description: String = "...",
    val thumbnail: String = "https://cdn.clipart.email/41a6da78b338fc8be770b2570a8e0260_250-2503958-potted-plants-clipart-transparent-background-plant-_820-669.jpeg",
    val trefle_plant: TrefleDetailedPlant? = null,
    val hydration: Int = 0
//    val time: Date = GregorianCalendar.getInstance().time
) : Parcelable

typealias PlantsResponse = Map<String, Plant>
