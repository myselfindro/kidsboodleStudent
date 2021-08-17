package com.mkc.school.ui.attendance

import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceResponse


interface AttendanceNavigator {
    fun successAttendanceResponse(attendanceResponse: AttendanceResponse?)
    fun errorAttendanceResponse(throwable: Throwable?)
}
