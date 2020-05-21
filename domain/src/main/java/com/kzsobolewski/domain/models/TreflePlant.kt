package com.kzsobolewski.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TreflePlant(
    val common_name: String,
    val id: Int = 0,
    val link: String = "",
    val scientific_name: String = ""
) : Parcelable