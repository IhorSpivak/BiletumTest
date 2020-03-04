package com.example.biletum.data.network.model.responses.events

data class EventsListResponse(
    val Limit:Int,
    val Offset:Int,
    val Total:Int,
    val List:List<EventItemResponse>
)

