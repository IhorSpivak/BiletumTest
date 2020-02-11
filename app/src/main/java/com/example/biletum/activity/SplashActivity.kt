package com.example.biletum.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import com.example.biletum.Helper.IS_AUTHORISATION
import com.example.biletum.R
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)


//        if( sharedPreferences.getBoolean(IS_AUTHORISATION,false)){
//            Handler().postDelayed({
//
//                val intent = Intent(this@SplashActivity, MainActivity::class.java)
//                startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
//            }, 1000)
//        } else{
//            Handler().postDelayed({
//
//                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
//                startActivity(intent,
//                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
//            }, 1000)
//        }



    }
}
