package com.mkc.school.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mkc.school.ApplicationClass
import com.mkc.school.data.datasource.api.ApiService
import com.mkc.school.data.datasource.sharedpref.AppSharedPref

import java.lang.ref.WeakReference

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

//    val isLoading = ObservableBoolean()

    private var mNavigator: WeakReference<N>? = null

    var navigator: N
        get() = mNavigator!!.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    protected val apiServiceWithJacksonFactory: ApiService
        get() = ApplicationClass.instance!!.apiServiceWithJacksonFactory

    val apiServiceWithGsonFactory: ApiService
        get() = ApplicationClass.instance!!.apiServiceWithGsonFactory

    val appSharedPref: AppSharedPref?
        get() = ApplicationClass.instance!!.appSharedPref


    protected val _scheduler_computation: Scheduler
        get() = Schedulers.computation()

    protected val _scheduler_io: Scheduler
        get() = Schedulers.io()

    val _scheduler_ui: Scheduler
        get() = AndroidSchedulers.mainThread()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
