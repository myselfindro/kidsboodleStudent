package com.mkc.school.ui.changepassword

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
   private var otp : String =""
   private var emailId : String =""
   private var phoneNumber : String =""
   private var dialCode : String =""

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
            binding?.llPasswordLayout?.visibility = View.VISIBLE
        }
        else if (pagefrom.equals("FORGOT_CHANGE_PASSWORD")){

            otp = getIntent().getStringExtra("OTP")!!
            emailId = getIntent().getStringExtra("EMAIL_ID")!!
            phoneNumber = getIntent().getStringExtra("PHONE_NO")!!
            dialCode = getIntent().getStringExtra("DIAL_CODE")!!
            binding?.tilOtp?.visibility = View.VISIBLE

            println("getIntentData : -otp-- > "+otp)
            println("getIntentData : -emailId-- > "+emailId)
            println("getIntentData : -phoneNumber-- > "+phoneNumber)
            println("getIntentData : -dialCode-- > "+dialCode)
        }


        initView()
    }

    private fun initView() {

        binding?.etOtp?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.length > 0) {
                    if (s.toString().trim().equals(otp)){
                        hideKeyboard()
                        binding?.tilOtp?.error = null
                        binding?.llPasswordLayout?.visibility = View.VISIBLE
                        binding?.tilOtp?.visibility = View.GONE
                        CommonUtils.showSuccessSnackbar(
                            this@ChangePasswordActivity,
                            binding?.mainLayout!!,
                            "Success"
                        )
                    }
                    else{
                        binding?.tilOtp?.error = getString(R.string.error_invalid_otp)
                        binding?.llPasswordLayout?.visibility = View.GONE
                        binding?.tilOtp?.visibility = View.VISIBLE
                        //hideKeyboard()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

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