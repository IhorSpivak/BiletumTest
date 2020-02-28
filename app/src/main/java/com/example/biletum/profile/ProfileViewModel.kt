package com.example.biletum.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.BaseViewModel
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.domain.ProfileIteractor
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel @Inject constructor(private val profileIteractor: ProfileIteractor): BaseViewModel() {


    private val _profileData = MutableLiveData<Response<ProfileResponse>>()
    val profileData: LiveData<Response<ProfileResponse>>
        get() = _profileData


    fun getProfile(token: String){
        uiScope.launch {
            val response = profileIteractor.invoke(ProfileIteractor.Params(token))
                    _profileData.value = response
        }
    }


    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}