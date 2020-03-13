package com.example.biletum.api

import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.requests.login.LoginRequest
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
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
        @Field("code") code: String,
        @Field("platform") platform: String

    ): Response<LoginConfirmResponse>

    @FormUrlEncoded
    @POST("/event")
    suspend fun addEvent(
        @Header("X-Token-Key") authorization:String,
        @Field("title") title:String,
        @Field("date_start") date_start: String,
        @Field("date_end") date_end: String

    ): EventAddResponse


    @GET("/event/list")
    suspend fun getEvents(
        @Header("X-Token-Key") authorization:String

    ): EventsListResponse


    @GET("/user/info")
    suspend fun getUserProfile(
        @Header("X-Token-Key") authorization:String
    ): ProfileResponse

}
