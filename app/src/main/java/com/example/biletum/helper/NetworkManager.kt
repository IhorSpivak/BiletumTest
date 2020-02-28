package com.example.biletum.helper

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.biletum.R
import com.example.biletum.data.network.model.ErrorMessage
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorManager {

    fun showError(response: Response<*>, context: Context) {
        when (response.code()) {
            400, 401, 403, 404, 409, 500 -> try {
                val gson = Gson()
                val message = gson.fromJson<ErrorMessage>(
                    response.errorBody()!!.string(),
                    ErrorMessage::class.java
                )
                Toast.makeText(context, message.error, Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            else -> Toast.makeText(context, context.getString(R.string.unknown_error_msg) + response.code(), Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun handleThrowable(context: Context, t: Throwable) {
        if (t is UnknownHostException || t is SocketTimeoutException) {
            Toast.makeText(context, R.string.msg_no_internet, Toast.LENGTH_SHORT).show()
        }
    }
}