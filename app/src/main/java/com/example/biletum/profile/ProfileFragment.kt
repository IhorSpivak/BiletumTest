package com.example.biletum.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.data.network.model.responses.ProfileResponse
import com.example.biletum.fragments.BaseFragment
import com.example.biletum.helper.ErrorManager
import retrofit2.Response
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
            when (it.code() == 200) {
                true -> {
                    handleProfileSuccess()
                }
                false -> {
                    handleBadResponse(it)
                }
            }

        })

        viewModel.getProfile("")



    }

    private fun handleBadResponse(it: Response<ProfileResponse>) {
        context?.let { it1 -> ErrorManager.showError(it, it1) }
    }

    private fun handleProfileSuccess() {

    }


}
