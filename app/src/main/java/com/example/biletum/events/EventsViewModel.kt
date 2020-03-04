package com.example.biletum.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.BaseViewModel
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.example.biletum.domain.AddEventIteractor
import com.example.biletum.domain.EventsListInteractor
import com.example.biletum.domain.LoginConfirmIteractor
import com.example.biletum.domain.LoginIteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EventsViewModel @Inject constructor(private val addEventIteractor: AddEventIteractor,
                                          private val getListEventsInteractor : EventsListInteractor): BaseViewModel() {


    private val _addEventData = MutableLiveData<EventAddResponse>()
    val addEventData: LiveData<EventAddResponse>
        get() = _addEventData

    private val _getListEventsData = MutableLiveData<EventsListResponse>()
    val getListEventsData: LiveData<EventsListResponse>
        get() = _getListEventsData


    fun addEvent(token: String, eventAddRequest: EventAddRequest){
        uiScope.launch {
            val addEventData = addEventIteractor.invoke(AddEventIteractor.Params("00deda2a-096c-4afc-b335-81d6a19a415a",eventAddRequest))
            _addEventData.value = addEventData
        }
    }

    fun getListEvents(token: String){
        uiScope.launch {
            val getListEventsData = getListEventsInteractor.invoke(EventsListInteractor.Params("00deda2a-096c-4afc-b335-81d6a19a415a"))
            _getListEventsData.value = getListEventsData
        }
    }





    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}