package com.example.biletum.data.network.model.responses.events

data class EventItemResponse(
    val id:Int,
    val title:String,
    val descr:String,
    val image:String,
    val date_start:String,
    val date_end :String,
    val url:String,
    val photos:List<String>,
    val location:String,
    val agenda:String,
    val contact:String,
    val access_type:String
)