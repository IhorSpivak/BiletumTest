package com.example.biletum.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biletum.data.network.model.requests.events.AddEventRequest
import com.example.biletum.interactors.CityListInteractor
import com.example.biletum.interactors.CountryListInteractor
import javax.inject.Inject

class EventShareViewModel @Inject constructor(): BaseViewModel() {

    val title = MutableLiveData<Any>()
    val desription = MutableLiveData<Any>()
    val startDate = MutableLiveData<Any>()
    val endDate = MutableLiveData<Any>()
    val location = MutableLiveData<Any>()
    val type = MutableLiveData<Any>()
    val category = MutableLiveData<Any>()



    fun setTitle(msg:String){
        title.value = msg
    }

    fun setDescription(msg:String){
        desription.value = msg
    }

    fun setStartDay(msg:String){
        startDate.value = msg
    }
    fun setEndDay(msg:String){
        startDate.value = msg
    }
    fun setLocation(msg:String){
        location.value = msg
    }

    fun setType(msg:String){
        type.value = msg
    }

    fun setCategory(msg:String){
        category.value = msg
    }


}