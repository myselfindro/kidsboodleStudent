package com.mkc.school.ui.changepassword

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivityChangePasswordBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.utils.CommonUtils


class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel>(),
    ChangePasswordNavigator {

    private var changePasswordViewModel: ChangePasswordViewModel? = null
    private var binding: ActivityChangePasswordBinding? = null
    private var pagefrom : String = ""

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_change_password
    override val viewModel: ChangePasswordViewModel
        get() {
            changePasswordViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
                ChangePasswordViewModel::class.java
            )
            return changePasswordViewModel as ChangePasswordViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changePasswordViewModel!!.navigator = this
        binding = viewDataBinding

        pagefrom = getIntent().getStringExtra("PAGE_FROM")!!

        if (pagefrom.equals("SETTENGS_CHANGE_PASSWORD")){
            binding?.tilOtp?.visibility = View.GONE
        }
        else{
            binding?.tilOtp?.visibility = View.VISIBLE
        }


        initView()
    }

    private fun initView() {

        binding?.ivGoNext?.setOnClickListener {
            if (checkValidation()) {
                hideKeyboard()
            }
        }
    }

    private fun checkValidation(): Boolean {
        with(binding!!) {
            if (TextUtils.isEmpty(etOldPassword.text?.toString()?.trim())) {
                tilOldPassword.error = getString(R.string.error_old_password)
                etOldPassword.requestFocus()
                return false
            }

            else if (TextUtils.isEmpty(etNewPassword.text?.toString()?.trim())) {
                tilNewPassword.error = getString(R.string.error_new_password)
                etNewPassword.requestFocus()
                return false
            }

            else if (TextUtils.isEmpty(etConfirmPassword.text?.toString()?.trim())) {
                tilConfirmPassword.error = getString(R.string.error_confirm_password)
                etConfirmPassword.requestFocus()
                return false
            }

            else if (etConfirmPassword.text?.trim().toString().trim().equals(etNewPassword.text?.toString()?.trim())) {
                tilConfirmPassword.error = getString(R.string.error_password_match)
                etConfirmPassword.requestFocus()
                return false
            }
        }

        return true
    }
}