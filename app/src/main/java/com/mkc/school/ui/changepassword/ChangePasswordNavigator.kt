package com.mkc.school.ui.changepassword

import com.mkc.school.data.pojomodel.api.response.changepassword.ChangePasswordResponse


interface ChangePasswordNavigator {

    fun successChangePasswordResponse(changePasswordResponse: ChangePasswordResponse?)
    fun successChangeForgotPasswordResponse(changePasswordResponse: ChangePasswordResponse?)
    fun errorChangePasswordResponse(throwable: Throwable?)
}
