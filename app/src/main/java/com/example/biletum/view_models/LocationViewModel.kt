package com.example.biletum.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.biletum.data.network.model.responses.location.CityListResponse
import com.example.biletum.data.network.model.responses.location.CountryResponse
import com.example.biletum.interactors.CityListInteractor
import com.example.biletum.interactors.CountryListInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocationViewModel @Inject constructor(private val countryListInteractor: CountryListInteractor,
                                            private val cityListInteractor: CityListInteractor
): BaseViewModel() {





    private val _countryListData = MutableLiveData<CountryResponse>()
    val countryListData: LiveData<CountryResponse>
        get() = _countryListData

    private val _cityListData = MutableLiveData<CityListResponse>()
    val cityListData: LiveData<CityListResponse>
        get() = _cityListData

    fun getCountryList(token: String){
        uiScope.launch {
            val getListEventsData = countryListInteractor.invoke(CountryListInteractor.Params(token))
            _countryListData.value = getListEventsData
        }
    }



    fun getCityList(token: String,country_id : Int){
        uiScope.launch {
            val getListCityData = cityListInteractor.invoke(CityListInteractor.Params(token, country_id))
            _cityListData.value = getListCityData
        }
    }


    override fun handleException(coroutineContext: CoroutineContext, throwable: Throwable) {
        super.handleException(coroutineContext, throwable)
        //TODO handle exceptions, that thrown in  chain of requests, that calls in this ViewModel
    }

}