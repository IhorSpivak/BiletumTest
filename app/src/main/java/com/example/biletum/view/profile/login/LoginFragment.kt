package com.example.biletum.view.profile.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer

import com.example.biletum.view.profile.events.events_list.MainActivity
import com.example.biletum.view.profile.BaseFragment
import com.example.biletum.helper.USER_KEY
import com.github.florent37.kotlin.pleaseanimate.please
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat

import android.os.CountDownTimer
import android.R
import android.os.Handler
import com.example.biletum.data.network.model.responses.login.LoginConfirmResponse
import com.example.biletum.helper.IS_AUTHORISATION
import com.example.biletum.view_models.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.btn_back
import kotlinx.android.synthetic.main.fragment_login.name_text_input
import retrofit2.Response

class LoginFragment: BaseFragment(com.example.biletum.R.layout.fragment_login) {


    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private var  confirmationId: String = ""
    private var  confirmCanWork: Boolean = false
    private var  clearCode: Boolean = false
    private var  userPhone: String = ""
    private var  timer : CountDownTimer? = null
    val CLEAR_TEXT = ""


    override fun onResume() {
        super.onResume()
        hideKeyBoard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAnimation()
        hideKeyBoard()

        viewModel = getViewModel(LoginViewModel::class.java)
        viewModel.loginData.observe(this, Observer {
            when (it != null) {
                true -> {
                    handleLoginSuccess(it.confirmation_id)
                }
                false -> {
                    handleNotLogin()
                }
            }

        })
        viewModel.loginConfirmData.observe(this, Observer {
            when (it.isSuccessful) {
                true -> {
                    handleConfirmSuccess(it.body()!!.token)
                }
                false -> {
                    handleExeptionData(it)
                }
            }
        })


        btn_login.setOnClickListener {
            if(!ed_phone.text.isEmpty()) {
                when(tv_code.length()  + ed_phone.length()){
                    5,6,7,8,9,10,11,12 -> {
                        tv_alert.visibility = View.VISIBLE
                        tv_alert.text = "The phone number must be at least 12 digits long"

                    }
                    13,14,15,16,17 -> {
                        viewModel.login("+" + ed_phone.text.toString())
                        userPhone = ed_phone.text.toString()
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

                        please(duration = 300) {
                            animate(btn_back) toBe {
                                visible()
                            }
                        }.start()

                        please(duration = 100) {
                            animate(card_first) toBe {
                                invisible()
                            }
                        }.start()

                        please(duration = 100) {
                            animate(card_confirm) toBe {
                                visible()
                            }
                        }.start()
                        hideKeyBoard()

                    }
            }

            } else{
                name_text_input.background = ContextCompat.getDrawable(context!!, com.example.biletum.R.drawable.alert_bg_input)
                btn_clear_field.visibility = View.VISIBLE
                btn_list_countrys.visibility = View.GONE
                tv_alert.visibility = View.VISIBLE
                tv_alert.text = "Field can't be empty"
                tv_short_name.setTextColor(resources.getColor(com.example.biletum.R.color.coral))
                tv_code.setTextColor(resources.getColor(com.example.biletum.R.color.coral))
            }

        }
        btn_confirm.setOnClickListener {
            if(ed_secret.length() < 4){
                tv_alert_confirm_code.visibility = View.VISIBLE
                tv_alert_confirm_code.text = "The code is too short, input 4 digits"
            } else{
                if(cb.isChecked) {
                    viewModel.loginConfirm(confirmationId, ed_secret.getText().toString())
                    pb.visibility = View.VISIBLE
                } else{
                    tv_alert_cb_is_checked.visibility = View.VISIBLE
                    tv_alert_cb_is_checked.text = "Confirm that you are familiar with our rules"
                }
            }
        }
        btn_resend_code.setOnClickListener {
                viewModel.login(userPhone)
            showTimer()
            showSnackBarMessage("SMS c кодом отправлено")
            ed_secret.setText(CLEAR_TEXT)
        }

        btn_clear_field.setOnClickListener {
                btn_clear_field.visibility = View.GONE
                btn_list_countrys.visibility = View.VISIBLE
                ed_phone.setText(CLEAR_TEXT)
                ed_phone.requestFocus()
                tv_short_name.setTextColor(resources.getColor(com.example.biletum.R.color.colorPrimary))
                tv_code.setTextColor(resources.getColor(R.color.black))

        }

        btn_back.setOnClickListener {
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
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

            please(duration = 100) {
                animate(card_confirm) toBe {
                    invisible()
                }
            }.start()

            please(duration = 100) {
                animate(card_first) toBe {
                    visible()
                }
            }.start()

            please(duration = 0) {
                animate(btn_back) toBe {
                    invisible()
                }
            }.start()

            ed_secret.setText(CLEAR_TEXT)
            cb.isChecked = false

        }

        btn_list_countrys.setOnClickListener {
            val intent = Intent(activity, CountryListActivity::class.java)
            startActivityForResult(intent, 1)
        }

        tv_case_1.setOnClickListener {
            ed_secret.requestFocus()
            showKeyboard()
        }
        tv_case_2.setOnClickListener {
            ed_secret.requestFocus()
            showKeyboard()
        }
        tv_case_3.setOnClickListener {
            ed_secret.requestFocus()
            showKeyboard()
        }
        tv_case_4.setOnClickListener {
            ed_secret.requestFocus()
            showKeyboard()
        }

        cb.setOnCheckedChangeListener { buttonView, isChecked ->
            tv_alert_cb_is_checked.visibility = View.INVISIBLE
        }

        ed_phone.onFocusChangeListener = object : View.OnFocusChangeListener {
            override fun onFocusChange(view: View, hasFocus: Boolean) {
                if (!hasFocus) {
                    name_text_input.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                } else {
                    name_text_input.setBackgroundResource(com.example.biletum.R.drawable.focus_bg_input)
                }
            }
        }

        ed_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    name_text_input.setBackgroundResource(com.example.biletum.R.drawable.focus_bg_input)
                    tv_alert.visibility = View.INVISIBLE
                    tv_short_name.setTextColor(resources.getColor(com.example.biletum.R.color.colorPrimary))
                    tv_code.setTextColor(resources.getColor(R.color.black))

                }
            }
        })

        ed_secret.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
              when (ed_secret.text.length){
                  0 -> {
                      tv_case_1.text = CLEAR_TEXT
                      tv_case_2.text = CLEAR_TEXT
                      tv_case_3.text = CLEAR_TEXT
                      tv_case_4.text = CLEAR_TEXT
                      tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_focus_tv_code_item)
                      tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)

                  }
                  1 -> {
                      tv_case_1.text = s.substring(0)
                      tv_case_2.text = CLEAR_TEXT
                      tv_case_3.text = CLEAR_TEXT
                      tv_case_4.text = CLEAR_TEXT
                      tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_focus_tv_code_item)
                      tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)

                  }
                  2 -> {
                      tv_case_2.text = s.substring(1)
                      tv_case_3.text = CLEAR_TEXT
                      tv_case_4.text = CLEAR_TEXT
                      tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                      tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.bg_focus_tv_code_item)
                      tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)

                  }
                  3 -> {
                      if(clearCode){

                         ed_secret.setText(CLEAR_TEXT)
                          clearCode = false

                      } else {
                          tv_case_3.text = s.substring(2)
                          tv_case_4.text = CLEAR_TEXT
                          tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                          tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                          tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.bg_focus_tv_code_item)
                          tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                      }

                  }
                  4 -> {
                      tv_case_4.text = s.substring(3)
                      tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                      tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                      tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                      tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.bg_code_full)
                      tv_alert_confirm_code.visibility = View.INVISIBLE

                  }

              }
                when (ed_secret.getText().toString()){
                    "" -> {
                        tv_case_1.text = CLEAR_TEXT
                        tv_case_2.text = CLEAR_TEXT
                        tv_case_3.text = CLEAR_TEXT
                        tv_case_4.text = CLEAR_TEXT
                        tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.bg_focus_tv_code_item)
                        tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                        tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                        tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.backgraund_phone_field)
                    }
                }

            }
        })
    }

    private fun hideKeyBoard() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view!!.windowToken, 0)

    }

    private fun initAnimation() {
        please(0) {
            animate(card_confirm) toBe {
                scale(scaleX = 0f, scaleY = 0f)
            }
        }.start()

        please(duration = 0) {
            animate(btn_back) toBe {
                invisible()
            }
        }.start()

        please(duration = 0) {
            animate(card_confirm) toBe {
                invisible()
            }
        }.start()
    }

    private fun handleExeptionData(it: Response<LoginConfirmResponse>) {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view?.windowToken, 0)
            when (it.code()){
                404 ->{
                    tv_alert_confirm_code.visibility = View.VISIBLE
                    tv_alert_confirm_code.text = "Code is not valid"
                    clearCode = true
                    tv_case_1.setBackgroundResource(com.example.biletum.R.drawable.alert_bg_input)
                    tv_case_2.setBackgroundResource(com.example.biletum.R.drawable.alert_bg_input)
                    tv_case_3.setBackgroundResource(com.example.biletum.R.drawable.alert_bg_input)
                    tv_case_4.setBackgroundResource(com.example.biletum.R.drawable.alert_bg_input)

                }
                505 ->{
                    pb.visibility = View.GONE
                    showSnackBarMessage("Internal server Error")
                }

                401 ->{
                    pb.visibility = View.GONE
                    showSnackBarMessage("Internal server Error")
                }
            }
    }

    private fun showTimer() {
        tv_timer.visibility = View.VISIBLE
        btn_resend_code.visibility = View.GONE
        timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_timer.text = """Resend code (available in ${millisUntilFinished / 1000} sec)"""
            }
            override fun onFinish() {
                tv_timer.visibility = View.GONE
                btn_resend_code.visibility = View.VISIBLE

            }
        }.start()
    }

    private fun showSnackBarMessage(message: String) {
        Snackbar.make(
            tv_short_name,
            Html.fromHtml("<font color=\"#78E5B4\">$message</font>"),
            Snackbar.LENGTH_LONG
        ).apply {

            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(80, 0, 80, 80)
            params.gravity = Gravity.BOTTOM
            params.anchorGravity = Gravity.BOTTOM

            view.layoutParams = params
            view.background = resources.getDrawable(com.example.biletum.R.drawable.snackbar_background, null)
            show()
        }
    }


    private fun handleNotConfirm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    private fun handleConfirmSuccess(token: String) {
        sharedPreferences.edit().putString(USER_KEY, token).apply()
        sharedPreferences.edit().putBoolean(IS_AUTHORISATION, true).apply()
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view!!.windowToken, 0)
        showSnackBarMessage("Вы авторизовались")

        Handler().postDelayed(
            {
                onGoToMainScreen()
            }, 2000)


    }

    private fun onGoToMainScreen() {
        timer?.cancel()
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity!!.finish()

    }


    private fun handleNotLogin() {
        pb.visibility = View.GONE
    }

    private fun handleLoginSuccess(confirmationId: String) {
        this.confirmationId = confirmationId


    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                if (data != null) {
                        ed_phone.setText("")
                        val name = data!!.getStringExtra("name")
                        val code = data!!.getStringExtra("code")
                        tv_short_name.text = name
                        tv_code.text = "($code)"
                        Handler().postDelayed({ showSnackBar(data) }, 500)

                }

            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showSnackBar(data: Intent?) {
        Snackbar.make(
            tv_short_name,
            Html.fromHtml("<font color=\"#78E5B4\">Изминение сохранено</font>"),
            Snackbar.LENGTH_LONG
        ).apply {
            val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(80, 0, 80, 80)
            params.gravity = Gravity.BOTTOM
            params.anchorGravity = Gravity.BOTTOM

            view.layoutParams = params
            view.background = resources.getDrawable(com.example.biletum.R.drawable.snackbar_background, null)
            show()
        }
    }

    private fun showKeyboard() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }







}
