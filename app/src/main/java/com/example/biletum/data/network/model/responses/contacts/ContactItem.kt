package com.example.biletum.data.network.model.responses.contacts

data class ContactItem(
    val id:Int,
    val name:String,
    val organization:String,
    val user:User,
    val notes:String,
    val event_id:Int

)