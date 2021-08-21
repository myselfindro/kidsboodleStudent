package com.mkc.school.ui.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivityChangePasswordBinding
import com.mkc.school.databinding.ActivityForgotPasswordBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.forgotpassword.ForgotPasswordNavigator
import com.mkc.school.ui.forgotpassword.ForgotPasswordViewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel>(),
    ChangePasswordNavigator {

    private var changePasswordViewModel: ChangePasswordViewModel? = null
    private var binding: ActivityChangePasswordBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_change_password
    override val viewModel: ChangePasswordViewModel
        get() {
            changePasswordViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(ChangePasswordViewModel::class.java)
            return changePasswordViewModel as ChangePasswordViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changePasswordViewModel!!.navigator = this
        binding = viewDataBinding

        initView()
    }

    private fun initView() {

    }
}