package com.mkc.school.ui.timetable

import com.mkc.school.data.pojomodel.api.response.timetable.TimetableResponse


interface TimetableNavigator {

    fun successTimetableResponse(timetableResponse: TimetableResponse?)
    fun errorTimetableResponse(throwable: Throwable?)
}
