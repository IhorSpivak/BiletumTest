package com.example.biletum.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.view_models.BaseViewModel
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.example.biletum.interactors.LoginConfirmIteractor

import com.example.biletum.interactors.LoginIteractor
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginViewModel @Inject constructor(private val loginInteractor: LoginIteractor,
                                         private val loginConfirmIteractor: LoginConfirmIteractor
): BaseViewModel() {

    private val _loginData = MutableLiveData<LoginResponce>()
    val loginData: LiveData<LoginResponce>
        get() = _loginData

    private val _loginConfirmData = MutableLiveData<Response<LoginConfirmResponse>>()
    val loginConfirmData: LiveData<Response<LoginConfirmResponse>>
        get() = _loginConfirmData

    private val _loginExeptionData = MutableLiveData<Throwable>()
    val loginExeptionData: LiveData<Throwable>
        get() = _loginExeptionData




    fun login(phone:String){
        uiScope.launch {
            val loginData = loginInteractor.invoke(LoginIteractor.Params(phone))
            _loginData.value = loginData
        }
    }

    fun loginConfirm(confirmation_id:String, code:String){
        uiScope.launch {
            val loginConfirmData = loginConfirmIteractor.invoke(LoginConfirmIteractor.Params(confirmation_id,code, "android"))
            }
    }





    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        uiScope.launch {
            _loginExeptionData.value = throwable
        }

    }

}