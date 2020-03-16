package com.example.biletum.interactors

import com.example.biletum.repository.DataRepository
import com.example.biletum.data.network.model.responses.ProfileResponse

import javax.inject.Inject

class ProfileIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<ProfileIteractor.Params, ProfileResponse> {


    override suspend fun invoke(executeParams: Params): ProfileResponse {
        return dataRepository.getUserProfile(executeParams.token)

    }

    data class Params(val token: String)

}