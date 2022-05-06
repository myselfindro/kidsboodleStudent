package com.mkc.school.ui.liveclass

import com.mkc.school.data.pojomodel.api.response.liveclass.LiveclassResponse
import com.mkc.school.data.pojomodel.api.response.timetable.TimetableResponse


interface LiveclassNavigator {

    fun onClick()

    fun successLiveclassResponse(liveclassResponse: LiveclassResponse)
    fun errorLiveclassResponse(throwable: Throwable?)
    fun successUpcomingLiveclassResponse(liveclassResponse: LiveclassResponse)
    fun errorUpcomingLiveclassResponse(throwable: Throwable?)
}
