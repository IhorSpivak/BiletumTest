package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.yaroslavtupalo.knocs.data.DataRepository
import javax.inject.Inject

class LoginConfirmIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<LoginConfirmIteractor.Params, LoginConfirmResponse> {


    override suspend fun invoke(executeParams: Params): LoginConfirmResponse {
        return dataRepository.loginConfirm(executeParams.confirmation_id,executeParams.code)

    }

    data class Params(val confirmation_id : String, val code: String)

}