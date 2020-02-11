package com.yaroslavtupalo.knocs.data.local.db.entities

//TODO database class
data class UserEntity(
    val access_token:String,
    val token_type:String,
    val expires_in:Int,
    val scope:String
)