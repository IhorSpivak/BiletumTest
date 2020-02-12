package com.example.biletum.data.network.model.requests.login

data class LoginConfirmRequest(
    val confirmation_id :String,
    val code :String

)