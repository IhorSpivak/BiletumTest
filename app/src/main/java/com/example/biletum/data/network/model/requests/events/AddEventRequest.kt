package com.example.biletum.data.network.model.requests.events

import com.example.biletum.data.network.model.responses.events.EventItemResponse


data class AddEventRequest(
    var title  :String,
    var descr :String,
    val photos :List<String>,
    val agenda :List<String>,
    val date_start :String,
    val date_end :String,
    val url  :String,
    val categories :List<Int>,
    val location :String,
    val country_id :Int,
    val city_id :Int,
    val contact :String,
    var event_type_id  :Int)