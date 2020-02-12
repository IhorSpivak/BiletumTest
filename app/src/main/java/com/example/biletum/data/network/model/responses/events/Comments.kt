package com.example.biletum.data.network.model.responses.events

data class Comments(
    val id:Int,
    val user_id:Int,
    val event_id:Int,
    val text:String
)