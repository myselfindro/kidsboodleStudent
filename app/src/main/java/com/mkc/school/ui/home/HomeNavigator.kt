package com.mkc.school.ui.home

import com.mkc.school.data.pojomodel.api.response.home.HomeResponse


interface HomeNavigator {

    fun onClick()

    fun successHomeResponse(homeResponse: HomeResponse?)
    fun errorHomeResponse(throwable: Throwable?)
}
