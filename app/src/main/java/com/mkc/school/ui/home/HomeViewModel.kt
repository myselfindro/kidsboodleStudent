package com.mkc.school.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class HomeViewModel(application: Application)  : BaseViewModel<HomeNavigator, Any?>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }

    fun getDashboardDetails() {
        val disposable = apiServiceWithGsonFactory.getDashboardDetails()
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successHomeResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorHomeResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun getHolidaysList(pageSize: String, session: String, month: String, year: String) {
        val disposable = apiServiceWithGsonFactory.getHolidaysList(pageSize, session, month, year)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successHolidaysListResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorHomeResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun getDateWiseHolidaysList(pageSize: String, session: String, date: String) {
        val disposable = apiServiceWithGsonFactory.getDateWiseHolidaysList(pageSize, session, date)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successDateWiseHolidaysListResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorHomeResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
}
