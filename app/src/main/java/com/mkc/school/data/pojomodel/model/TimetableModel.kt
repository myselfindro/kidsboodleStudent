package com.mkc.school.data.pojomodel.model

data class TimetableModel(
    var subject: String? = null,
    var teacherName: String? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var isLiveClass: Int? = null,
)
