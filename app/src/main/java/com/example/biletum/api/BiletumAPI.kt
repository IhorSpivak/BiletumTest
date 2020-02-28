package com.example.biletum.api

import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import retrofit2.Response
import retrofit2.http.*

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

    @Multipart
    @PUT("/wallet/getUserWallets")
    suspend fun addEvent(
        @Header("Authorization") authorization:String,
        @Part eventAddRequest : EventAddRequest
    ): EventAddResponse

    @Multipart
    @PUT("/user/info")
    suspend fun getUserProfile(
        @Header("Authorization") authorization:String
    ): Response<ProfileResponse>

}
