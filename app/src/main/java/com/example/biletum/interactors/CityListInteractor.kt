package com.example.biletum.interactors

import com.example.biletum.data.network.model.responses.location.CityListResponse
import com.example.biletum.data.network.model.responses.location.CountryResponse
import com.example.biletum.repository.DataRepository
import javax.inject.Inject

class CityListInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<CityListInteractor.Params, CityListResponse> {


    override suspend fun invoke(executeParams: Params): CityListResponse {
        return dataRepository.getCityList(executeParams.token, executeParams.id)

    }

    data class Params(val token: String, val id: Int)

}