package com.mkc.school.ui.liveclass

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class LiveclassViewModel(application: Application)  : BaseViewModel<LiveclassNavigator, Any?>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }
    fun getLiveClassList(){
        val disposable = apiServiceWithGsonFactory.getLiveClassList()
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successLiveclassResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorLiveclassResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
    fun getUpcomingLiveClassList(){
        val disposable = apiServiceWithGsonFactory.getUpcomingLiveClassList()
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successLiveclassResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorLiveclassResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

}
