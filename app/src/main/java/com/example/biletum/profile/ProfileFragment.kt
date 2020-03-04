package com.example.biletum.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
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



    }

    private fun handleBadResponse() {
      
    }

    private fun handleProfileSuccess(it: ProfileResponse) {

        ed_name.setText(it.first_name)
        ed_mail.setText(it.email)
        ed_phone.setText(it.phone)

    }


}
