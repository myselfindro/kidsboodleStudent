package com.mkc.school.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson
import com.mkc.school.data.pojomodel.api.request.LoginRequest
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse
import com.mkc.school.ui.base.BaseViewModel

class LoginViewModel(application: Application)  : BaseViewModel<LoginNavigator>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }


    fun getCallLogin(loginRequest: LoginRequest) {
        val disposable = apiServiceWithGsonFactory.apiLogin(loginRequest)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    //appSharedPref?.userType = farmerRegistrationRequest.usertype
                    navigator.successLoginResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorLoginResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
}
