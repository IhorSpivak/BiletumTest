package com.example.biletum.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.BaseViewModel
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.example.biletum.domain.AddEventIteractor
import com.example.biletum.domain.LoginConfirmIteractor
import com.example.biletum.domain.LoginIteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EventsViewModel @Inject constructor(private val addEventIteractor: AddEventIteractor): BaseViewModel() {


    private val _addEventData = MutableLiveData<EventAddResponse>()
    val addEventData: LiveData<EventAddResponse>
        get() = _addEventData


    fun addEvent(eventAddRequest: EventAddRequest){
        uiScope.launch {
            val addEventData = addEventIteractor.invoke(AddEventIteractor.Params(eventAddRequest))
            _addEventData.value = addEventData
        }
    }





    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}