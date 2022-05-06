package com.mkc.school.ui.changepassword

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mkc.school.data.pojomodel.api.request.ChangePasswordRequest
import com.mkc.school.data.pojomodel.api.request.ChangePasswordWithEmailRequest
import com.mkc.school.data.pojomodel.api.request.ChangePasswordWithPhoneRequest
import com.mkc.school.data.pojomodel.api.request.ForgotPasswordWithPhoneRequest

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class ChangePasswordViewModel(application: Application)  : BaseViewModel<ChangePasswordNavigator, Any?>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }

    fun callChangePassword (changePasswordRequest: ChangePasswordRequest) {
        val disposable = apiServiceWithGsonFactory.callChangePassword(changePasswordRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successChangePasswordResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorChangePasswordResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun callChangePasswordWithEmail (changePasswordWithEmailRequest: ChangePasswordWithEmailRequest) {
        val disposable = apiServiceWithGsonFactory.callChangePasswordWithEmail(changePasswordWithEmailRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successChangeForgotPasswordResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorChangePasswordResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun callChangePasswordWithPhone (changePasswordWithPhoneRequest: ChangePasswordWithPhoneRequest) {
        val disposable = apiServiceWithGsonFactory.callChangePasswordWithPhone(changePasswordWithPhoneRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successChangeForgotPasswordResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorChangePasswordResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }


}
