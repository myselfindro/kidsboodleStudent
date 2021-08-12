package com.mkc.school.ui.account

import android.app.Application
import androidx.lifecycle.MutableLiveData

import com.mkc.school.data.pojomodel.api.response.CommonApiResponse
import com.mkc.school.ui.base.BaseViewModel

class AccountViewModel(application: Application)  : BaseViewModel<AccountNavigator>(application) {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var dataResponse: MutableLiveData<CommonApiResponse> = MutableLiveData()
    var errorInResponse: MutableLiveData<Throwable> = MutableLiveData()

    init {
        isLoading.value = false
    }
}
