package com.mkc.school.data.pojomodel.api.response.exam

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class StudentSubjectMarks(
    @field:JsonProperty("total_marks_obtained")
    @field:SerializedName("total_marks_obtained")
    val total_marks_obtained: Float? = null,

    @field:JsonProperty("exam_grade")
    @field:SerializedName("exam_grade")
    val exam_grade: Int? = null,

    @field:JsonProperty("result_publish_date")
    @field:SerializedName("result_publish_date")
    val result_publish_date: String? = null,

)
