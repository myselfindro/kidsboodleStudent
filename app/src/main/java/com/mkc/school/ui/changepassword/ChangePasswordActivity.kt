package com.mkc.school.ui.changepassword

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.request.ChangePasswordRequest
import com.mkc.school.data.pojomodel.api.request.ChangePasswordWithEmailRequest
import com.mkc.school.data.pojomodel.api.request.ChangePasswordWithPhoneRequest
import com.mkc.school.data.pojomodel.api.response.changepassword.ChangePasswordResponse
import com.mkc.school.databinding.ActivityChangePasswordBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.utils.CommonUtils


class ChangePasswordActivity :
    BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel>(),
    ChangePasswordNavigator {

    private var changePasswordViewModel: ChangePasswordViewModel? = null
    private var binding: ActivityChangePasswordBinding? = null
    private var pagefrom: String = ""
    private var otp: String = ""
    private var emailId: String = ""
    private var phoneNumber: String = ""
    private var dialCode: String = ""
    private var selectedType: String = ""

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

        if (pagefrom.equals("SETTENGS_CHANGE_PASSWORD")) {
            binding?.tilOtp?.visibility = View.GONE
            binding?.llPasswordLayout?.visibility = View.VISIBLE
        } else if (pagefrom.equals("FORGOT_CHANGE_PASSWORD")) {

            otp = getIntent().getStringExtra("OTP")!!
            emailId = getIntent().getStringExtra("EMAIL_ID")!!
            phoneNumber = getIntent().getStringExtra("PHONE_NO")!!
            dialCode = getIntent().getStringExtra("DIAL_CODE")!!
            selectedType = getIntent().getStringExtra("SELECTED_TYPE")!!
            binding?.tilOtp?.visibility = View.VISIBLE

            println("getIntentData : -otp-- > " + otp)
            println("getIntentData : -emailId-- > " + emailId)
            println("getIntentData : -phoneNumber-- > " + phoneNumber)
            println("getIntentData : -dialCode-- > " + dialCode)
            println("getIntentData : -selectedType-- > " + selectedType)
        }


        initView()
    }

    private fun initView() {

        binding?.etOtp?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.length > 0) {
                    if (s.toString().trim().equals(otp)) {
                        hideKeyboard()
                        binding?.tilOtp?.error = null
                        binding?.llPasswordLayout?.visibility = View.VISIBLE
                        binding?.tilOtp?.visibility = View.GONE
                        binding?.tilOldPassword?.visibility = View.GONE
                        binding?.tilUsername?.visibility = View.VISIBLE
                        CommonUtils.showSuccessSnackbar(
                            this@ChangePasswordActivity,
                            binding?.mainLayout!!,
                            "Success"
                        )
                    } else {
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

            if (pagefrom.equals("SETTENGS_CHANGE_PASSWORD")) {
                if (checkValidation()) {
                    hideKeyboard()
                    var changePasswordRequest = ChangePasswordRequest()
                    changePasswordRequest.auth_provider = "student"
                    changePasswordRequest.old_password =
                        binding?.etOldPassword?.text?.toString()?.trim()
                    changePasswordRequest.new_password =
                        binding?.etNewPassword?.text?.toString()?.trim()
                    viewModel.callChangePassword(changePasswordRequest)
                }
            } else if (pagefrom.equals("FORGOT_CHANGE_PASSWORD")) {
                if (checkForgotValidation()) {
                    if (selectedType.equals("EMAIL")) {
                        var changePasswordWithEmailRequest = ChangePasswordWithEmailRequest()
                        changePasswordWithEmailRequest.auth_provider = "student"
                        changePasswordWithEmailRequest.email = emailId
                        changePasswordWithEmailRequest.username =
                            binding?.etUsername?.text?.toString()?.trim()
                        changePasswordWithEmailRequest.new_password =
                            binding?.etNewPassword?.text?.toString()?.trim()
                        changePasswordWithEmailRequest.confirm_password =
                            binding?.etConfirmPassword?.text?.toString()?.trim()
                        viewModel.callChangePasswordWithEmail(changePasswordWithEmailRequest)
                    } else if (selectedType.equals("PHONE")) {
                        var changePasswordWithPhoneRequest = ChangePasswordWithPhoneRequest()
                        changePasswordWithPhoneRequest.auth_provider = "student"
                        changePasswordWithPhoneRequest.phone = phoneNumber
                        changePasswordWithPhoneRequest.username =
                            binding?.etUsername?.text?.toString()?.trim()
                        changePasswordWithPhoneRequest.new_password =
                            binding?.etNewPassword?.text?.toString()?.trim()
                        changePasswordWithPhoneRequest.confirm_password =
                            binding?.etConfirmPassword?.text?.toString()?.trim()
                        viewModel.callChangePasswordWithPhone(changePasswordWithPhoneRequest)
                    }
                }
            }


        }
    }

    private fun checkValidation(): Boolean {
        with(binding!!) {
            if (TextUtils.isEmpty(etOldPassword.text?.toString()?.trim())) {
                tilOldPassword.error = getString(R.string.error_old_password)
                etOldPassword.requestFocus()
                return false
            } else if (TextUtils.isEmpty(etNewPassword.text?.toString()?.trim())) {
                tilNewPassword.error = getString(R.string.error_new_password)
                etNewPassword.requestFocus()
                return false
            } else if (TextUtils.isEmpty(etConfirmPassword.text?.toString()?.trim())) {
                tilConfirmPassword.error = getString(R.string.error_confirm_password)
                etConfirmPassword.requestFocus()
                return false
            } else if (!etConfirmPassword.text?.toString()?.trim()
                    .equals(etNewPassword.text?.toString()?.trim())
            ) {
                tilConfirmPassword.error = getString(R.string.error_password_match)
                etConfirmPassword.requestFocus()
                return false
            }
        }

        return true
    }

    private fun checkForgotValidation(): Boolean {
        with(binding!!) {
            if (TextUtils.isEmpty(etUsername.text?.toString()?.trim())) {
                tilUsername.error = getString(R.string.error_username)
                etUsername.requestFocus()
                return false
            } else if (TextUtils.isEmpty(etNewPassword.text?.toString()?.trim())) {
                tilNewPassword.error = getString(R.string.error_new_password)
                etNewPassword.requestFocus()
                return false
            } else if (TextUtils.isEmpty(etConfirmPassword.text?.toString()?.trim())) {
                tilConfirmPassword.error = getString(R.string.error_confirm_password)
                etConfirmPassword.requestFocus()
                return false
            } else if (!etConfirmPassword.text?.toString()?.trim()
                    .equals(etNewPassword.text?.toString()?.trim())
            ) {
                tilConfirmPassword.error = getString(R.string.error_password_match)
                etConfirmPassword.requestFocus()
                return false
            }
        }

        return true
    }

    override fun successChangePasswordResponse(changePasswordResponse: ChangePasswordResponse?) {
        if (changePasswordResponse?.request_status == 1) {
            CommonUtils.showSuccessSnackbar(
                this,
                binding?.mainLayout!!,
                changePasswordResponse?.result?.msg!!
            )

        } else {
            CommonUtils.showErrorSnackbar(
                this,
                binding?.mainLayout!!,
                changePasswordResponse?.result?.msg!!
            )
        }

    }

    override fun successChangeForgotPasswordResponse(changePasswordResponse: ChangePasswordResponse?) {
        if (changePasswordResponse?.request_status == 1) {
            CommonUtils.showSuccessSnackbar(
                this,
                binding?.mainLayout!!,
                changePasswordResponse?.result?.msg!!
            )

        } else {
            CommonUtils.showErrorSnackbar(
                this,
                binding?.mainLayout!!,
                changePasswordResponse?.result?.msg!!
            )
        }
    }

    override fun errorChangePasswordResponse(throwable: Throwable?) {
        hideLoading()
        if (throwable?.message != null) {
            println("throwable : "+throwable.message)
            if (throwable?.message.equals("HTTP 404 Not Found")) {
                CommonUtils.showErrorSnackbar(this!!, binding?.mainLayout!!, "Matching User does not exist!")
            }
            else if (throwable?.message.equals("HTTP 409 Conflict")){
                CommonUtils.showErrorSnackbar(this!!, binding?.mainLayout!!, "Your new password is similar to old password. Please try with another password.")
            }
            else{
                CommonUtils.showErrorSnackbar(this!!, binding?.mainLayout!!, "Something went wrong")
            }
        }

    }
}