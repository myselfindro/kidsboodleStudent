package com.mkc.school.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.request.ForgotPasswordWithEmailRequest
import com.mkc.school.data.pojomodel.api.request.ForgotPasswordWithPhoneRequest
import com.mkc.school.data.pojomodel.api.response.forgotpassword.ForgotPasswordResponse
import com.mkc.school.databinding.ActivityForgotPasswordBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.changepassword.ChangePasswordActivity
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.showErrorSnackbar
import com.mkc.school.utils.CommonUtils.showSuccessSnackbar

class ForgotPasswordActivity :
    BaseActivity<ActivityForgotPasswordBinding, ForgotPasswordViewModel>(),
    ForgotPasswordNavigator {

    private var forgotPasswordViewModel: ForgotPasswordViewModel? = null
    private var binding: ActivityForgotPasswordBinding? = null
    private var selectedType: String? = ""


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_forgot_password
    override val viewModel: ForgotPasswordViewModel
        get() {
            forgotPasswordViewModel = ViewModelProvider(
                this,
                ViewModelFactory(application)
            ).get(ForgotPasswordViewModel::class.java)
            return forgotPasswordViewModel as ForgotPasswordViewModel
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotPasswordViewModel!!.navigator = this
        binding = viewDataBinding

        initView()
    }

    private fun initView() {

        binding?.etEmailId?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    binding?.etMobileNo?.setText("")
                    selectedType = "EMAIL"
                }
            }
        })

        binding?.etMobileNo?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    binding?.etEmailId?.setText("")
                    selectedType = "PHONE"
                }
            }
        })

        binding?.ivGoNext?.setOnClickListener {

            if (selectedType.equals("")) {
                showErrorSnackbar(
                    this,
                    binding?.mainLayout!!,
                    "Please Enter your Email id / Phone No."
                )
            } else if (selectedType.equals("EMAIL")) {
                if (!CommonUtils.isEmailValid(binding?.etEmailId?.text?.trim().toString().trim())) {
                    binding?.tilEmailId?.error = getString(R.string.error_email_valid)
                    binding?.tilEmailId?.requestFocus()
                }
                else{
                    var forgotPasswordWithEmailRequest = ForgotPasswordWithEmailRequest()
                    forgotPasswordWithEmailRequest.email_id = "stdnt.test@gmail.com"
                    viewModel.callForgotPasswordWithEmail(forgotPasswordWithEmailRequest)
                }
            } else if (selectedType.equals("PHONE")) {
                if (binding?.etMobileNo?.text?.trim().toString().trim().length<10) {
                    binding?.tilMobileNo?.error = getString(R.string.error_phone_valid)
                    binding?.tilMobileNo?.requestFocus()
                }
                else{
                    var forgotPasswordWithPhoneRequest = ForgotPasswordWithPhoneRequest()
                    forgotPasswordWithPhoneRequest.dial_code = "+91"
                    forgotPasswordWithPhoneRequest.phone_no = "652295262"
                    viewModel.callForgotPasswordWithPhone(forgotPasswordWithPhoneRequest)
                }
            }
        }

    }

    override fun successForgotPasswordResponse(forgotPasswordResponse: ForgotPasswordResponse?) {
        if (forgotPasswordResponse?.request_status == 1) {
            showSuccessSnackbar(this, binding?.mainLayout!!, forgotPasswordResponse?.result?.otp!!)

            var emailId : String =""
            var phoneNumber : String =""
            var dialCode : String =""
            if (forgotPasswordResponse?.result?.email_id!=null){
                emailId= forgotPasswordResponse?.result?.email_id
            }

            if (forgotPasswordResponse?.result?.Phone_number!=null){
                phoneNumber= forgotPasswordResponse?.result?.Phone_number
            }

            if (forgotPasswordResponse?.result?.dial_code!=null){
                dialCode= forgotPasswordResponse?.result?.dial_code
            }

            val i = Intent(this, ChangePasswordActivity::class.java)
            i.putExtra("PAGE_FROM","FORGOT_CHANGE_PASSWORD")
            i.putExtra("OTP",forgotPasswordResponse?.result?.otp)
            i.putExtra("EMAIL_ID",emailId)
            i.putExtra("PHONE_NO",phoneNumber)
            i.putExtra("DIAL_CODE",dialCode)
            i.putExtra("SELECTED_TYPE",selectedType)
            startActivity(i)
            finish()

        } else {
            showErrorSnackbar(this, binding?.mainLayout!!, forgotPasswordResponse?.result?.otp!!)
        }
    }

    override fun errorForgotPasswordResponse(throwable: Throwable?) {
        hideLoading()
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(this!!, binding?.mainLayout!!, "Something went wrong")
            //Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

}