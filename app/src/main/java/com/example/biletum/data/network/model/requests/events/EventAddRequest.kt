package com.example.biletum.data.network.model.requests.events

data class EventAddRequest(
    val title  :String,
    val descr :String,
    val image :String,
    val date_start :String,
    val date_end :String,
    val region :String,
    val url  :String,
    val photos :String,
    val location :String,
    val agenda :String,
    val contact :String,
    val access_type :String)