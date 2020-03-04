package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.DataRepository
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import javax.inject.Inject

class EventsListInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<EventsListInteractor.Params, EventsListResponse> {


    override suspend fun invoke(executeParams: Params): EventsListResponse {
        return dataRepository.getEvents(executeParams.token)

    }

    data class Params(val token: String)

}