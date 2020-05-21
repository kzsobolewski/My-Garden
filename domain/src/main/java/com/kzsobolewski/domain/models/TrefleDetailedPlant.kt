package com.kzsobolewski.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrefleDetailedPlant(
    val common_name: String,
    val division: Division?,
    val family_common_name: String,
    val genus: Genus?,
    val scientific_name: String
) : Parcelable

@Parcelize
data class Genus(
    val name: String
) : Parcelable

@Parcelize
data class Division(
    val name: String
) : Parcelable

