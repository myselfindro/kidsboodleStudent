package com.mkc.school

import android.app.Application
import com.mkc.school.data.datasource.api.ApiClient
import com.mkc.school.data.datasource.api.ApiService
import com.mkc.school.data.datasource.sharedpref.AppSharedPref
import com.mkc.school.utils.AppConstants

class ApplicationClass : Application() {

    var appSharedPref: AppSharedPref? = null
        private set


    val apiServiceWithJacksonFactory: ApiService
        get() = ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_JACKSON)!!.create(ApiService::class.java)

    val apiServiceWithGsonFactory: ApiService
        get() = ApiClient.retrofit(this, getString(R.string.api_base), ApiClient.CONVERTER_TYPE_GSON)!!.create(ApiService::class.java)


    override fun onCreate() {
        super.onCreate()
        instance = this
        appSharedPref = AppSharedPref(this, AppConstants.SHARED_PREF_NAME)

    }

    companion object {

        @get:Synchronized
        var instance: ApplicationClass? = null
            private set
    }
}
