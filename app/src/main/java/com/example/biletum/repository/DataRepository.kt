package  com.example.biletum.repository

import android.content.SharedPreferences
import com.example.biletum.api.BiletumAPI
import com.example.biletum.data.network.model.requests.events.AddEventRequest
import com.example.biletum.data.network.model.requests.events.EventsListRequest
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.data.network.model.responses.events.*
import com.example.biletum.data.network.model.responses.location.CityListResponse
import com.example.biletum.data.network.model.responses.location.CountryResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import okhttp3.MultipartBody
import retrofit2.Response

import javax.inject.Inject

class DataRepository(private val apiService:BiletumAPI) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    suspend fun login(phone:String): LoginResponce {
       val loginResponse =  apiService.login(phone)


        return loginResponse

    }

    suspend fun addEvent(token:String, addRequest: AddEventRequest): EventAddResponse {
        val response =  apiService.addEvent(token,addRequest.title, addRequest.date_start, addRequest.date_end,addRequest.event_type_id,
            addRequest.categories,addRequest.agenda,addRequest.contact,addRequest.photos)

        return response

    }

    suspend fun getEvents(token:String,  request: EventsListRequest): EventsListResponse{
        val response =  apiService.getEvents(token,request.offset,request.limit,
            request.filter,request.type)

        return response

    }

    suspend fun getEventType(token:String): EventTypeResponse{
        val response =  apiService.getEventTypeList(token)

        return response
    }

    suspend fun getEventCategory(token:String): EventCategoryResponse{
        val response =  apiService.getEventCategoryList(token,100,100,100)

        return response

    }

    suspend fun getCountyList(token:String): CountryResponse{
        val response =  apiService.getCountryList(token)

        return response

    }

    suspend fun getCityList(token:String, county_id: Int): CityListResponse{
        val response =  apiService.getCityList(token, county_id)

        return response

    }

    suspend fun imageUpload(token:String, image: MultipartBody.Part): ImageUploadResponse{
        val response =  apiService.uploadImage(token, image)

        return response

    }

    suspend fun getUserProfile(token:String): ProfileResponse {
        val response =  apiService.getUserProfile(token)

        return response

    }

    suspend fun loginConfirm(confirmation_id:String, code:String, platform:String):Response<LoginConfirmResponse>{
        val loginConfirmResponse =  apiService.loginConfirm(confirmation_id =  confirmation_id, code = code,platform = platform)

        return loginConfirmResponse

    }





}