package com.example.biletum.activity

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import com.example.biletum.R
import com.example.biletum.helper.IS_AUTHORISATION
import javax.inject.Inject

class SplashActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)



                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle())


    }
}
