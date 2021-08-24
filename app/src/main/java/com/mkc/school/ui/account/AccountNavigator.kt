package com.mkc.school.ui.account

import com.mkc.school.data.pojomodel.api.response.exam.StudentExamsResponse
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileResponse


interface AccountNavigator {
    fun successAccountProfileResponse(accountProfileResponse: AccountProfileResponse?)
    fun successStudentExamResponse(studentExamsResponse: StudentExamsResponse?)
    fun errorAccountProfileResponse(throwable: Throwable?)
}
