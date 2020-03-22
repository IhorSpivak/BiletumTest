package com.example.biletum.interactors

import com.example.biletum.data.network.model.responses.events.EventCategoryResponse
import com.example.biletum.data.network.model.responses.location.CountryResponse
import com.example.biletum.repository.DataRepository
import javax.inject.Inject

class CountryListInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<CountryListInteractor.Params, CountryResponse> {


    override suspend fun invoke(executeParams: Params): CountryResponse {
        return dataRepository.getCountyList(executeParams.token)

    }

    data class Params(val token: String)

}