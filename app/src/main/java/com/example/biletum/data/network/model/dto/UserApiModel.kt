package com.example.biletum.data.network.model.dto

//TODO network model
data class UserApiModel(
    val access_token:String,
    val token_type:String,
    val expires_in:Int,
    val scope:String
)