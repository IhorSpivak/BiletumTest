package com.example.biletum.data.network.model.requests.contacts

data class ContactAddRequst(
    val user_id :Int,
    val name :String,
    val organization :String,
    val event_id :Int
)