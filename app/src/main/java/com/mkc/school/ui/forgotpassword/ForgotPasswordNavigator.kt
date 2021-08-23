package com.mkc.school.ui.forgotpassword

import com.mkc.school.data.pojomodel.api.response.forgotpassword.ForgotPasswordResponse


interface ForgotPasswordNavigator {

    fun successForgotPasswordResponse(forgotPasswordResponse: ForgotPasswordResponse?)
    fun errorForgotPasswordResponse(throwable: Throwable?)
}
