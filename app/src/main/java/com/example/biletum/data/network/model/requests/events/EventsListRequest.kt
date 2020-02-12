package com.example.biletum.data.network.model.requests.events


data class EventsListRequest(
    val offset :Int,
    val limit :Int,
    val date_start :Int,
    val date_end :Int,
    val filter :String,
    val type :String

)