package com.mkc.school.ui.teacher

import com.mkc.school.data.pojomodel.api.response.teachers.TeachersResponse


interface TeachersNavigator {

    fun successTeachersResponse(teachersResponse: TeachersResponse?)
    fun errorTeachersResponse(throwable: Throwable?)
}
