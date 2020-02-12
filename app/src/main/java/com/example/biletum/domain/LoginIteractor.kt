package com.example.biletum.domain

import com.example.biletum.base.interactor.Interactor
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.yaroslavtupalo.knocs.data.DataRepository
import javax.inject.Inject

class LoginIteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<LoginIteractor.Params, LoginResponce> {


    override suspend fun invoke(executeParams: Params): LoginResponce {
        return dataRepository.login(executeParams.phone)

    }

    data class Params(val phone : String)

}