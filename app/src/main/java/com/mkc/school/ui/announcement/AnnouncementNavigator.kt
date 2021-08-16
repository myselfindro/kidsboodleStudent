package com.mkc.school.ui.announcement

import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementListResponse


interface AnnouncementNavigator {

    fun onClick()

    fun successAnnouncementResponse(announcementListResponse: AnnouncementListResponse?)
    fun errorAnnouncementResponse(throwable: Throwable?)
}
