package com.example.biletum.interactors

import com.example.biletum.data.network.model.responses.events.EventCategoryResponse
import com.example.biletum.data.network.model.responses.events.EventTypeResponse
import com.example.biletum.repository.DataRepository
import javax.inject.Inject

class CategoryEventInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<CategoryEventInteractor.Params, EventCategoryResponse> {


    override suspend fun invoke(executeParams: Params): EventCategoryResponse {
        return dataRepository.getEventCategory(executeParams.token)

    }

    data class Params(val token: String)

}