package com.example.biletum.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.responses.events.*
import com.example.biletum.interactors.AddEventIteractor
import com.example.biletum.interactors.CategoryEventInteractor
import com.example.biletum.interactors.EventsListInteractor
import com.example.biletum.interactors.TypeEventInteractor
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EventsViewModel @Inject constructor(private val addEventInteractor: AddEventIteractor,
                                          private val getListEventsInteractor : EventsListInteractor,
                                          private val getListCategoryInteractor : CategoryEventInteractor,
                                          private val uploadImageInteractor : UploadImageInteractor,
                                          private val getEventsTypeInteractor : TypeEventInteractor
): BaseViewModel() {


    private val _addEventData = MutableLiveData<EventAddResponse>()
    val addEventData: LiveData<EventAddResponse>
        get() = _addEventData

    private val _getListEventsData = MutableLiveData<EventsListResponse>()
    val getListEventsData: LiveData<EventsListResponse>
        get() = _getListEventsData

    private val _getListTypeEvents = MutableLiveData<EventTypeResponse>()
    val getListTypeEvents: LiveData<EventTypeResponse>
        get() = _getListTypeEvents

    private val _getListCategoryEvents = MutableLiveData<EventCategoryResponse>()
    val getListCategoryEvents: LiveData<EventCategoryResponse>
        get() = _getListCategoryEvents


    private val _uploadImageData = MutableLiveData<ImageUploadResponse>()
    val uploadImageData: LiveData<ImageUploadResponse>
        get() = _uploadImageData


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

    fun getTypesEvent(token: String){
        uiScope.launch {
            val getListEventType = getEventsTypeInteractor.invoke(TypeEventInteractor.Params(token))
            _getListTypeEvents.value = getListEventType
        }
    }


    fun getCategoryEvent(token: String){
        uiScope.launch {
            val getListEventCategory = getListCategoryInteractor.invoke(CategoryEventInteractor.Params(token))
            _getListCategoryEvents.value = getListEventCategory
        }
    }

    fun uploadImage(token: String, file : MultipartBody.Part){
        uiScope.launch {
            val uploadImageData = uploadImageInteractor.invoke(UploadImageInteractor.Params(token, file))
            _uploadImageData.value = uploadImageData
        }
    }







    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}