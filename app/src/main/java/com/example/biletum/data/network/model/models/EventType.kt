package com.example.biletum.data.network.model.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventType(
    val name:String,
    val id:Int,
    var isCheked :Boolean = false
): Parcelable