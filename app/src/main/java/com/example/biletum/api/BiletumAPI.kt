package com.example.biletum.api

import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.data.network.model.responses.events.*
import com.example.biletum.data.network.model.responses.location.CityListResponse
import com.example.biletum.data.network.model.responses.location.CountryResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import okhttp3.MultipartBody
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
        @Field("date_end") date_end: String,
        @Field("event_type_id") event_type_id:Int,
        @Field("categories") categories:List<Int>,
        @Field("agenda") agenda:List<String>,
        @Field("contact") contact:String,
        @Field("photos") photos:List<String>
    ): EventAddResponse


    @GET("/event/list")
    suspend fun getEvents(
        @Header("X-Token-Key") authorization:String,
        @Query("offset") offset : Int,
        @Query("limit") limit: Int,
        @Query("filter") filter : String,
        @Query("type") type : String
    ): EventsListResponse

    @GET("/event_type/list")
    suspend fun getEventTypeList(
        @Header("X-Token-Key") authorization:String

    ): EventTypeResponse

    @GET("/category/list")
    suspend fun getEventCategoryList(
        @Header("X-Token-Key") authorization:String,
        @Query("offset") offset : Int,
        @Query("limit") limit: Int,
        @Query("total") total: Int
    ): EventCategoryResponse


    @GET("/country/list")
    suspend fun getCountryList(
        @Header("X-Token-Key") authorization:String
    ): CountryResponse

    @GET("/city/list")
    suspend fun getCityList(
        @Header("X-Token-Key") authorization:String,
        @Query("country_id") id : Int
    ): CityListResponse

    @Multipart
    @POST("/upload/file")
    suspend fun uploadImage(
        @Header("X-Token-Key") authorization:String,
        @Part image: MultipartBody.Part
    ):ImageUploadResponse


    @GET("/user/info")
    suspend fun getUserProfile(
        @Header("X-Token-Key") authorization:String
    ): ProfileResponse

}
