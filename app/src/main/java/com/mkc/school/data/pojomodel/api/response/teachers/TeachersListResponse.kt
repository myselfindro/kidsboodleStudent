package com.mkc.school.data.pojomodel.api.response.teachers

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeachersListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    val auth_provider: String? = null,

    @field:JsonProperty("first_name")
    @field:SerializedName("first_name")
    val first_name: String? = null,

    @field:JsonProperty("last_name")
    @field:SerializedName("last_name")
    val last_name: String? = null,

    @field:JsonProperty("image")
    @field:SerializedName("image")
    val image: String? = null,

    @field:JsonProperty("email")
    @field:SerializedName("email")
    val email: String? = null,

    @field:JsonProperty("dob")
    @field:SerializedName("dob")
    val dob: String? = null,

    @field:JsonProperty("address1")
    @field:SerializedName("address1")
    val address1: String? = null,

    @field:JsonProperty("address2")
    @field:SerializedName("address2")
    val address2: String? = null,

    @field:JsonProperty("education")
    @field:SerializedName("education")
    val education: String? = null,

    @field:JsonProperty("gender")
    @field:SerializedName("gender")
    val gender: String? = null,

    @field:JsonProperty("pin_code")
    @field:SerializedName("pin_code")
    val pin_code: String? = null,

    @field:JsonProperty("dial_code")
    @field:SerializedName("dial_code")
    val dial_code: String? = null,

    @field:JsonProperty("phone")
    @field:SerializedName("phone")
    val phone: String? = null,

    @field:JsonProperty("is_active")
    @field:SerializedName("is_active")
    val is_active: Boolean? = null,

    @field:JsonProperty("is_deleted")
    @field:SerializedName("is_deleted")
    val is_deleted: Boolean? = null,

    @field:JsonProperty("created_at")
    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:JsonProperty("updated_at")
    @field:SerializedName("updated_at")
    val updated_at: String? = null,

    @field:JsonProperty("user")
    @field:SerializedName("user")
    val user: Int? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    @field:JsonProperty("roll")
    @field:SerializedName("roll")
    val roll: Int? = null,

    @field:JsonProperty("country_code")
    @field:SerializedName("country_code")
    val country_code: Int? = null,

    @field:JsonProperty("state_code")
    @field:SerializedName("state_code")
    val state_code: Int? = null,

    @field:JsonProperty("city_code")
    @field:SerializedName("city_code")
    val city_code: Int? = null,

    @field:JsonProperty("created_by")
    @field:SerializedName("created_by")
    val created_by: String? = null,

    @field:JsonProperty("owned_by")
    @field:SerializedName("owned_by")
    val owned_by: String? = null,

    @field:JsonProperty("updated_by")
    @field:SerializedName("updated_by")
    val updated_by: String? = null,

    @field:JsonProperty("teacher_subject")
    @field:SerializedName("teacher_subject")
    val teacher_subject: ArrayList<TeacherSubject>? = null
)
