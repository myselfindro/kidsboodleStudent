package com.mkc.school.data.pojomodel.api.response.grade

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class GradeResponse(

    @field:JsonProperty("request_status")
    @field:SerializedName("request_status")
    val request_status: Int? = null,

    @field:JsonProperty("msg")
    @field:SerializedName("msg")
    val msg: String? = null,

    @field:JsonProperty("results")
    @field:SerializedName("results")
    val result: ArrayList<GradeListResponse>? = null
)
