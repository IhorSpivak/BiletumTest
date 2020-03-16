package com.example.biletum.interactors

import com.example.biletum.repository.DataRepository
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse

import javax.inject.Inject

class AddEventIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<AddEventIteractor.Params, EventAddResponse> {


    override suspend fun invoke(executeParams: Params): EventAddResponse {
        return dataRepository.addEvent(executeParams.token,executeParams.eventAddRequest)

    }

    data class Params(val token: String,val eventAddRequest: EventAddRequest)

}