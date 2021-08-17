package com.mkc.school.data.pojomodel.api.response.attendance

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttendanceDetails(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("date")
    @field:SerializedName("date")
    val date: String? = null,

    @field:JsonProperty("teacher")
    @field:SerializedName("teacher")
    val teacher: String? = null,

    @field:JsonProperty("subject")
    @field:SerializedName("subject")
    val subject: String? = null,

    @field:JsonProperty("start_time")
    @field:SerializedName("start_time")
    val start_time: String? = null,

    @field:JsonProperty("end_time")
    @field:SerializedName("end_time")
    val end_time: String? = null,

    @field:JsonProperty("present_status")
    @field:SerializedName("present_status")
    val present_status: String? = null,

    @field:JsonProperty("teacher_remarks")
    @field:SerializedName("teacher_remarks")
    val teacher_remarks: String? = null,

    @field:JsonProperty("student_remarks")
    @field:SerializedName("student_remarks")
    val student_remarks: String? = null,
)
