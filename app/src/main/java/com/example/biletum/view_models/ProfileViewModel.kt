package com.example.biletum.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.view_models.BaseViewModel
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.interactors.ProfileIteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ProfileViewModel @Inject constructor(private val profileIteractor: ProfileIteractor): BaseViewModel() {


    private val _profileData = MutableLiveData<ProfileResponse>()
    val profileData: LiveData<ProfileResponse>
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