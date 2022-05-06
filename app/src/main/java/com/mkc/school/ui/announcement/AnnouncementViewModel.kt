package com.mkc.school.ui.announcement

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class AnnouncementViewModel(application: Application)  : BaseViewModel<AnnouncementNavigator, Any?>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }


    fun getAnnouncementList(pageSize: String) {
        val disposable = apiServiceWithGsonFactory.getAnnouncementList(pageSize)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successAnnouncementResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorAnnouncementResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
}
