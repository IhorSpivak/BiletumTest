package com.example.biletum.view.profile.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.helper.IS_AUTHORISATION
import com.example.biletum.helper.USER_KEY
import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.view.profile.login.LoginActivity
import com.example.biletum.view_models.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.ed_phone
import javax.inject.Inject

class ProfileFragment: BaseFragment(R.layout.fragment_profile) {


    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var  confirmationId: String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = getViewModel(ProfileViewModel::class.java)
        viewModel.profileData.observe(this, Observer {
            when (it != null) {
                true -> {
                    handleProfileSuccess(it)
                }
                false -> {
                    handleBadResponse()
                }
            }

        })

        viewModel.getProfile("00deda2a-096c-4afc-b335-81d6a19a415a")

        btn_exit.setOnClickListener {
           onLogout()
        }

    }

    private fun handleBadResponse() {
      
    }

    private fun handleProfileSuccess(it: ProfileResponse) {
        ed_name.setText(it.first_name)
        ed_mail.setText(it.email)
        ed_phone.setText(it.phone)

    }

    private fun onLogout() {
        sharedPreferences.edit().putBoolean(IS_AUTHORISATION, false).apply()
        sharedPreferences.edit().putString(USER_KEY, "").apply()
        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity!!.finish()

    }


}
