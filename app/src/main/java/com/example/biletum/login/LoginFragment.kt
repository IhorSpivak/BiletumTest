package com.example.biletum.login

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import com.example.biletum.R
import com.example.biletum.activity.AddEventActivity
import com.example.biletum.activity.MainActivity
import com.example.biletum.fragments.BaseFragment
import com.example.biletum.helper.USER_KEY
import com.github.florent37.kotlin.pleaseanimate.please
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment: BaseFragment(R.layout.fragment_login) {


    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var  confirmationId: String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        please(0) {
            animate(card_confirm) toBe {
                scale(scaleX = 0f, scaleY = 0f)
            }
        }.start()

        viewModel = getViewModel(LoginViewModel::class.java)
        viewModel.loginData.observe(this, Observer {
            when (it.confirmation_id != null) {
                true -> {
                    handleLoginSuccess(it.confirmation_id)
                }
                false -> {
                    handleNotLogin()
                }
            }

        })
        viewModel.loginConfirmData.observe(this, Observer {
            when (it.token != null) {
                true -> {
                    handleConfirmSuccess(it.token)
                }
                false -> {
                    handleNotConfirm()
                }
            }
        })

        btn_login.setOnClickListener {
            viewModel.login(ed_phone.text.toString())
            please(duration = 300) {
                animate(card_first) toBe {
                    scale(scaleX = 0f, scaleY = 0f)
                }
            }.start()
            please(duration = 300) {
                animate(card_confirm) toBe {
                    scale(scaleX = 1f, scaleY = 1f)
                }
            }.start()
        }

        btn_confirm.setOnClickListener {
//            viewModel.loginConfirm(confirmationId, ed_phone.text.toString())
            please(duration = 300) {
                animate(card_first) toBe {
                    scale(scaleX = 1f, scaleY = 1f)
                }
            }.start()

            please(duration = 300) {
                animate(card_confirm) toBe {
                    scale(scaleX = 0f, scaleY = 0f)
                }
            }.start()

        }
    }

    private fun handleNotConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleConfirmSuccess(token: String) {
        sharedPreferences.edit().putString(USER_KEY, token).apply()

        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())

        val snack = Snackbar.make(btn_login, Html.fromHtml("<font color=\"#F8941E\">Вы авторизовались</font>"), Snackbar.LENGTH_LONG)
        snack.show()
    }


    private fun handleNotLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleLoginSuccess(confirmationId: String) {
        this.confirmationId = confirmationId
        ed_phone.text!!.clear()
        ed_phone.hint = "Введите код подтверждения"
        btn_confirm.visibility = View.VISIBLE
        btn_login.visibility = View.GONE

    }



}
