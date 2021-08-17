package com.mkc.school.data.pojomodel.api.response.attendance

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttendanceListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("student")
    @field:SerializedName("student")
    val student: Int? = null,

    @field:JsonProperty("school_user")
    @field:SerializedName("school_user")
    val school_user: Int? = null,

    @field:JsonProperty("date")
    @field:SerializedName("date")
    val date: String? = null,

    @field:JsonProperty("day_name")
    @field:SerializedName("day_name")
    val day_name: String? = null,

    @field:JsonProperty("present_status")
    @field:SerializedName("present_status")
    val present_status: String? = null,

    @field:JsonProperty("teacher_remarks")
    @field:SerializedName("teacher_remarks")
    val teacher_remarks: String? = null,

    @field:JsonProperty("student_remarks")
    @field:SerializedName("student_remarks")
    val student_remarks: String? = null,

    @field:JsonProperty("is_deleted")
    @field:SerializedName("is_deleted")
    val is_deleted: Boolean? = null,

    @field:JsonProperty("created_at")
    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:JsonProperty("updated_at")
    @field:SerializedName("updated_at")
    val updated_at: String? = null,

    @field:JsonProperty("created_by")
    @field:SerializedName("created_by")
    val created_by: String? = null,

    @field:JsonProperty("owned_by")
    @field:SerializedName("owned_by")
    val owned_by: String? = null,

    @field:JsonProperty("updated_by")
    @field:SerializedName("updated_by")
    val updated_by: String? = null,

    @field:JsonProperty("day_type")
    @field:SerializedName("day_type")
    val day_type: String? = null,

    @field:JsonProperty("attendence_details")
    @field:SerializedName("attendence_details")
    val attendence_details: ArrayList<AttendanceDetails>? = null
)
