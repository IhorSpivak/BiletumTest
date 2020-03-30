package com.example.biletum.data.network.model.responses.events

import android.os.Parcelable
import com.example.biletum.data.network.model.models.EventType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventTypeResponse(

    val List:List<EventType>
): Parcelable
