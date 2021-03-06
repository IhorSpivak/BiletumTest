package com.example.biletum.view.profile.start_splash

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.biletum.R
import com.example.biletum.helper.IS_AUTHORISATION
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.activity.BaseActivity
import com.example.biletum.view.profile.login.LoginActivity
import com.example.biletum.view.profile.events.events_list.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {



    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)


        if(sharedPreferences.getString(USER_KEY, "").toString().isNotEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }
}
