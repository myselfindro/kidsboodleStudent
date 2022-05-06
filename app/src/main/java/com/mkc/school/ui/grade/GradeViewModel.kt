package com.mkc.school.ui.grade

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

import com.mkc.school.ui.base.BaseViewModel
import com.mkc.school.data.pojomodel.api.response.CommonApiResponse

class GradeViewModel(application: Application)  : BaseViewModel<GradeNavigator, Any?>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }

    fun getGradeList(pageSize: String, examType :String) {
        val disposable = apiServiceWithGsonFactory.getGradeList(pageSize, examType)
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successGradeResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorGradeResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }

    fun getExamList() {
        val disposable = apiServiceWithGsonFactory.getExamList()
            .subscribeOn(_scheduler_io)
            .observeOn(_scheduler_ui)
            .subscribe({ response ->
                if (response != null) {
                    Log.d("check_response", ": " + Gson().toJson(response))
                    navigator.successExamResponse(response)

                } else {
                    Log.d("check_response", ": null response")
                }
            }, { throwable ->
                run {
                    navigator.errorGradeResponse(throwable)
                    Log.d("check_response_error", ": " + throwable.message)
                }
            })

        compositeDisposable.add(disposable)
    }
}
