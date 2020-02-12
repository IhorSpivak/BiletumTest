package com.example.biletum.api

import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BiletumAPI {


    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("phone") phone: String
    ): LoginResponce


    @FormUrlEncoded
    @POST("/login_confirm")
    suspend fun loginConfirm(
        @Field("confirmation_id") confirmation_id: String,
        @Field("code") code: String

    ): LoginConfirmResponse

}
