package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.DataRepository
import com.example.biletum.data.network.model.responses.ProfileResponse

import retrofit2.Response
import javax.inject.Inject

class ProfileIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<ProfileIteractor.Params, ProfileResponse>{


    override suspend fun invoke(executeParams: Params): ProfileResponse {
        return dataRepository.getUserProfile(executeParams.token)

    }

    data class Params(val token: String)

}