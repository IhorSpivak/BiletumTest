package com.example.biletum.data.network.model.requests.events


data class EventsListRequest(
    val offset :Int,
    val limit :Int,
    val filter :String,
    val type :String

)