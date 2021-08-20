package com.mkc.school.ui.home

import com.mkc.school.data.pojomodel.api.response.home.HolidayResponse
import com.mkc.school.data.pojomodel.api.response.home.HomeResponse


interface HomeNavigator {

    fun successHomeResponse(homeResponse: HomeResponse?)
    fun successHolidaysListResponse(holidayResponse: HolidayResponse?)
    fun successDateWiseHolidaysListResponse(holidayResponse: HolidayResponse?)
    fun errorHomeResponse(throwable: Throwable?)
}
