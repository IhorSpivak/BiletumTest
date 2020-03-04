package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.DataRepository
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.requests.events.EventDeleteRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce

import javax.inject.Inject

class AddEventIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<AddEventIteractor.Params, EventAddResponse> {


    override suspend fun invoke(executeParams: Params): EventAddResponse {
        return dataRepository.addEvent(executeParams.token,executeParams.eventAddRequest)

    }

    data class Params(val token: String,val eventAddRequest: EventAddRequest)

}