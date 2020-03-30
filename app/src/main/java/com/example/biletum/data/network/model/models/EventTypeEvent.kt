package com.example.biletum.data.network.model.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventTypeEvent(
    val list: List<EventType>


) : Parcelable