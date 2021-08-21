package com.mkc.school.ui.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivityForgotPasswordBinding
import com.mkc.school.databinding.ActivitySplashBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.splash.SplashNavigator
import com.mkc.school.ui.splash.SplashViewModel

class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding, ForgotPasswordViewModel>(),
    ForgotPasswordNavigator {

    private var forgotPasswordViewModel: ForgotPasswordViewModel? = null
    private var binding: ActivityForgotPasswordBinding? = null



    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_forgot_password
    override val viewModel: ForgotPasswordViewModel
        get() {
            forgotPasswordViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(ForgotPasswordViewModel::class.java)
            return forgotPasswordViewModel as ForgotPasswordViewModel
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordViewModel!!.navigator = this
        binding = viewDataBinding

        initView()
    }

    private fun initView() {


    }

}