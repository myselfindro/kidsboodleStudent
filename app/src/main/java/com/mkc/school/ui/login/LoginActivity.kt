package com.mkc.school.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.request.LoginRequest
import com.mkc.school.data.pojomodel.api.response.login.LoginResponse
import com.mkc.school.databinding.ActivityLoginBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.dashboard.DashboardActivity
import com.mkc.school.ui.successscreen.SuccessScreenActivity
import com.mkc.school.utils.CommonUtils.showErrorSnackbar
import com.mkc.school.utils.CommonUtils.showSuccessSnackbar

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    private var loginViewModel: LoginViewModel? = null
    private var binding: ActivityLoginBinding? = null



    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() {
            loginViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(LoginViewModel::class.java)
            return loginViewModel as LoginViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel!!.navigator = this
        binding = viewDataBinding

        binding?.btnLogin?.setOnClickListener {
            if (checkValidation()) {
                hideKeyboard()
                binding?.btnLogin?.startAnimation()

                var loginRequest = LoginRequest()
                loginRequest.auth_provider = "student"
                loginRequest.username = binding?.etUserId?.text.toString().trim()
                loginRequest.password = binding?.etPassword?.text.toString().trim()
                viewModel.getCallLogin(loginRequest)
            }

//            var loginRequest = LoginRequest()
//            loginRequest.auth_provider = "student"
//            loginRequest.username = "testtest1995"
//            loginRequest.password = "testtest1995"
//            viewModel.getCallLogin(loginRequest)

        }
    }

    private fun checkValidation(): Boolean {
        with(binding!!) {
            if (TextUtils.isEmpty(etUserId.text?.trim())) {
                tilStudentId.error = getString(R.string.error_userid)
                etUserId.requestFocus()
                return false
            }
//            else if (!CommonUtils.isEmailValid(etEmail.text?.trim().toString())) {
//                etEmail.error = getString(R.string.error_email_valid)
//                etEmail.requestFocus()
//                return false
//            }
            else if (TextUtils.isEmpty(etPassword.text?.trim().toString())) {
                tilPassword.error = getString(R.string.error_password)
                etPassword.requestFocus()
                return false
            }
        }

        return true
    }

    override fun onClick() {
    }

    override fun successLoginResponse(loginResponse: LoginResponse?) {
        if (loginResponse?.request_status == 1) {
            //showSuccessSnackbar(this, binding?.mainLayout!!, loginResponse.msg!!)
            val i = Intent(this, SuccessScreenActivity::class.java)
            startActivity(i)
            finish()
        } else {
            binding?.btnLogin?.revertAnimation()
            //showErrorSnackbar(this, binding?.mainLayout!!, loginResponse?.msg!!)
        }
    }

    override fun errorLoginResponse(throwable: Throwable?) {
//        hideLoading()
        binding?.btnLogin?.revertAnimation()
        if (throwable?.message != null) {
            showErrorSnackbar(this!!, binding?.mainLayout!!, "Something went wrong")
            //Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}