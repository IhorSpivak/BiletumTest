package com.example.biletum.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.BaseViewModel
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.example.biletum.domain.LoginConfirmIteractor

import com.example.biletum.domain.LoginIteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginViewModel @Inject constructor(private val loginInteractor: LoginIteractor,
                                         private val loginConfirmIteractor: LoginConfirmIteractor): BaseViewModel() {

    private val _loginData = MutableLiveData<LoginResponce>()
    val loginData: LiveData<LoginResponce>
        get() = _loginData

    private val _loginConfirmData = MutableLiveData<LoginConfirmResponse>()
    val loginConfirmData: LiveData<LoginConfirmResponse>
        get() = _loginConfirmData




    fun login(phone:String){
        uiScope.launch {
            val loginData = loginInteractor.invoke(LoginIteractor.Params(phone))
            _loginData.value = loginData
        }
    }

    fun loginConfirm(confirmation_id:String, code:String){
        uiScope.launch {
            val loginConfirmData = loginConfirmIteractor.invoke(LoginConfirmIteractor.Params(confirmation_id,code))
            _loginConfirmData.value = loginConfirmData
        }
    }





    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}