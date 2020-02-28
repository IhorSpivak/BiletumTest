package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.yaroslavtupalo.knocs.data.DataRepository
import retrofit2.Response
import javax.inject.Inject

class ProfileIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<ProfileIteractor.Params, Response<ProfileResponse>>{


    override suspend fun invoke(executeParams: Params): Response<ProfileResponse> {
        return dataRepository.getUserProfile()

    }

    data class Params(val token: String)

}