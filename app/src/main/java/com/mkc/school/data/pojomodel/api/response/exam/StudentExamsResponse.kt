package com.mkc.school.data.pojomodel.api.response.exam

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class StudentExamsResponse(
    @field:JsonProperty("request_status")
    @field:SerializedName("request_status")
    val request_status: Int? = null,

    @field:JsonProperty("msg")
    @field:SerializedName("msg")
    val msg: String? = null,

    @field:JsonProperty("results")
    @field:SerializedName("results")
    val result: ArrayList<StudentExamsListResponse>? = null
)
