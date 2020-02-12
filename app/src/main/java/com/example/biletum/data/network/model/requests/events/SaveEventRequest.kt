package com.example.biletum.data.network.model.requests.events

data class SaveEventRequest(
    val id: Int,
    val title: String,
    val descr: String,
    val image: String,
    val date_start: String,
    val date_end: String,
    val photos: List<String>,
    val location: String,
    val agenda: String,
    val contact: String,
    val access_type: String,
    val url: String
)
