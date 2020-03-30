package com.example.biletum.data.network.model.responses.events

import com.example.biletum.data.network.model.models.Agenda
import com.example.biletum.data.network.model.models.City
import com.example.biletum.data.network.model.models.Country
import com.example.biletum.data.network.model.models.Personal

data class EventItemResponse(
    val id:Int,
    val title:String,
    val descr:String,
    val image:String? = "",
    val date_start:String,
    val date_end :String,
    val url:String,
    val photos:List<String>,
    val location:String,
    val agenda:List<Agenda>,
    val contact:String,
    val access_type:String,
    val country: Country,
    val city: City,
    val address:String,
    val personal: Personal
)