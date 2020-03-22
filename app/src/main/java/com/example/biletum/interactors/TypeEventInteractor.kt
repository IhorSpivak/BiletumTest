package com.example.biletum.interactors

import com.example.biletum.data.network.model.responses.events.EventTypeResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import com.example.biletum.repository.DataRepository
import javax.inject.Inject

class TypeEventInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<TypeEventInteractor.Params, EventTypeResponse> {


    override suspend fun invoke(executeParams: Params): EventTypeResponse {
        return dataRepository.getEventType(executeParams.token)

    }

    data class Params(val token: String)

}