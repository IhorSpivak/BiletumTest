package com.example.biletum.data.network.model.models

data class ContactItem(
    val id:Int,
    val name:String,
    val organization:String,
    val user: User,
    val notes:String,
    val event_id:Int

)