package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.DataRepository
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import retrofit2.Response

import javax.inject.Inject

class LoginConfirmIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<LoginConfirmIteractor.Params,Response<LoginConfirmResponse>> {


    override suspend fun invoke(executeParams: Params): Response<LoginConfirmResponse> {
        return dataRepository.loginConfirm(executeParams.confirmation_id,executeParams.code, executeParams.platform)

    }

    data class Params(val confirmation_id : String, val code: String, val platform: String)

}