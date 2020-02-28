package com.example.biletum.data.network.model

import com.example.biletum.data.network.model.responses.contacts.User

data class ErrorMessage(
    val code:Int,
    val error:String,
    val request_id:String


)