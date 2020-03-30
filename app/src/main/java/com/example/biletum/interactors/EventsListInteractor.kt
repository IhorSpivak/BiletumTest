package com.example.biletum.interactors

import com.example.biletum.data.network.model.requests.events.EventsListRequest
import com.example.biletum.repository.DataRepository
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import javax.inject.Inject

class EventsListInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<EventsListInteractor.Params, EventsListResponse> {


    override suspend fun invoke(executeParams: Params): EventsListResponse {
        return dataRepository.getEvents(executeParams.token, executeParams.request)

    }

    data class Params(val token: String, val request: EventsListRequest)

}