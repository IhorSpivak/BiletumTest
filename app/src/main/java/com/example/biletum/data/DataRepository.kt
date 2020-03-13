package  com.example.biletum.data

import android.content.SharedPreferences
import com.example.biletum.api.BiletumAPI
import com.example.biletum.data.network.model.requests.events.EventAddRequest
import com.example.biletum.data.network.model.requests.events.EventsListRequest
import com.example.biletum.data.network.model.requests.login.LoginRequest
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.data.network.model.responses.events.EventAddResponse
import com.example.biletum.data.network.model.responses.events.EventsListResponse
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.data.network.model.responses.login.LoginResponce
import com.example.biletum.helper.USER_KEY
import retrofit2.Response

import javax.inject.Inject

class DataRepository(private val apiService:BiletumAPI) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    suspend fun login(phone:String): LoginResponce {
       val loginResponse =  apiService.login(phone)


        return loginResponse

    }

    suspend fun addEvent(token:String, addRequest: EventAddRequest): EventAddResponse {
        val response =  apiService.addEvent(token,addRequest.title, addRequest.date_start, addRequest.date_end )

        return response

    }

    suspend fun getEvents(token:String): EventsListResponse{
        val response =  apiService.getEvents(token)

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