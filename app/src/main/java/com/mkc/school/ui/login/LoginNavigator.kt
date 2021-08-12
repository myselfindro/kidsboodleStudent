package com.mkc.school.ui.login

import com.mkc.school.data.pojomodel.api.response.login.LoginResponse


interface LoginNavigator {

    fun onClick()

    fun successLoginResponse(loginResponse: LoginResponse?)
    fun errorLoginResponse(throwable: Throwable?)
}
