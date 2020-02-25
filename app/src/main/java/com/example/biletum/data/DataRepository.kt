package com.yaroslavtupalo.knocs.data

import android.content.SharedPreferences
import com.example.biletum.api.BiletumAPI
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce

import javax.inject.Inject

class DataRepository(private val apiService:BiletumAPI) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    suspend fun login(phone:String): LoginResponce {
       val loginResponse =  apiService.login(phone =  phone)


        return loginResponse

    }

    suspend fun addEvent(addRequest: EventAddRequest): EventAddResponse {
        val response =  apiService.addEvent("",addRequest)

        return response

    }

    suspend fun loginConfirm(confirmation_id:String, code:String): LoginConfirmResponse {
        val loginConfirmResponse =  apiService.loginConfirm(confirmation_id =  confirmation_id, code = code)


        return loginConfirmResponse

    }





}