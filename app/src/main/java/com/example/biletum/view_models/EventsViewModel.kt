package com.example.biletum.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.view_models.BaseViewModel
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import com.example.biletum.interactors.AddEventIteractor
import com.example.biletum.interactors.EventsListInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EventsViewModel @Inject constructor(private val addEventInteractor: AddEventIteractor,
                                          private val getListEventsInteractor : EventsListInteractor
): BaseViewModel() {


    private val _addEventData = MutableLiveData<EventAddResponse>()
    val addEventData: LiveData<EventAddResponse>
        get() = _addEventData

    private val _getListEventsData = MutableLiveData<EventsListResponse>()
    val getListEventsData: LiveData<EventsListResponse>
        get() = _getListEventsData


    fun addEvent(token: String, eventAddRequest: EventAddRequest){
        uiScope.launch {
            val addEventData = addEventInteractor.invoke(AddEventIteractor.Params("00deda2a-096c-4afc-b335-81d6a19a415a",eventAddRequest))
            _addEventData.value = addEventData
        }
    }

    fun getListEvents(token: String){
        uiScope.launch {
            val getListEventsData = getListEventsInteractor.invoke(EventsListInteractor.Params(token))
            _getListEventsData.value = getListEventsData
        }
    }





    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}