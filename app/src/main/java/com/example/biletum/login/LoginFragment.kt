package com.example.biletum.login

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.fragments.BaseFragment
import com.github.florent37.kotlin.pleaseanimate.please
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: BaseFragment(R.layout.fragment_login) {


    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = getViewModel(LoginViewModel::class.java)
        viewModel.loginData.observe(this, Observer {
            when (it.confirmation_id != null) {
                true -> {
                    handleLoginSuccess()
                }
                false -> {
                    handleNotLogin()
                }
            }

        })

        viewModel.loginConfirmData.observe(this, Observer {
            when (it.token != null) {
                true -> {
                    handleConfirmSuccess()
                }
                false -> {
                    handleNotConfirm()
                }
            }

        })

        btn_login.setOnClickListener {
            viewModel.login(ed_phone.text.toString())
        }
    }

    private fun handleNotConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleConfirmSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun handleNotLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleLoginSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
