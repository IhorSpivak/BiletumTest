package com.example.biletum.data.network.model.responses

data class ProfileResponse(
    val id:Int,
    val phone:String,
    val email:String,
    val first_name:String,
    val last_name:String,
    val middle_name:String,
    val birthday:String,
    val country:String
)