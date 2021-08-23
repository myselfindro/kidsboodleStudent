package com.mkc.school.ui.forgotpassword

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mkc.school.data.pojomodel.api.request.ForgotPasswordWithEmailRequest
import com.mkc.school.data.pojomodel.api.request.ForgotPasswordWithPhoneRequest
import com.mkc.school.data.pojomodel.api.request.LoginRequest

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class ForgotPasswordViewModel(application: Application)  : BaseViewModel<ForgotPasswordNavigator>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }

    fun callForgotPasswordWithEmail(forgotPasswordWithEmailRequest: ForgotPasswordWithEmailRequest) {
        val disposable = apiServiceWithGsonFactory.callForgotPasswordWithEmail(forgotPasswordWithEmailRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successForgotPasswordResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorForgotPasswordResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun callForgotPasswordWithPhone(forgotPasswordWithPhoneRequest: ForgotPasswordWithPhoneRequest) {
        val disposable = apiServiceWithGsonFactory.callForgotPasswordWithPhone(forgotPasswordWithPhoneRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successForgotPasswordResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorForgotPasswordResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
}
