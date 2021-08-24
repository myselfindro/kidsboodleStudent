package com.mkc.school.data.pojomodel.api.response.profile

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class AccountProfileDataResponse(

    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("attendence")
    @field:SerializedName("attendence")
    val attendence: Int? = null,

    @field:JsonProperty("country_code")
    @field:SerializedName("country_code")
    val country_code: Int? = null,

    @field:JsonProperty("state_code")
    @field:SerializedName("state_code")
    val state_code: Int? = null,

    @field:JsonProperty("city_code")
    @field:SerializedName("city_code")
    val city_code: Int? = null,

    @field:JsonProperty("class_name")
    @field:SerializedName("class_name")
    val class_name: String? = null,

    @field:JsonProperty("section")
    @field:SerializedName("section")
    val section: String? = null,

    @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    val auth_provider: String? = null,

    @field:JsonProperty("student_fname")
    @field:SerializedName("student_fname")
    val student_fname: String? = null,

    @field:JsonProperty("student_lname")
    @field:SerializedName("student_lname")
    val student_lname: String? = null,

    @field:JsonProperty("image")
    @field:SerializedName("image")
    val image: String? = null,

    @field:JsonProperty("address1")
    @field:SerializedName("address1")
    val address1: String? = null,

    @field:JsonProperty("address2")
    @field:SerializedName("address2")
    val address2: String? = null,

    @field:JsonProperty("pin_code")
    @field:SerializedName("pin_code")
    val pin_code: String? = null,

    @field:JsonProperty("gender")
    @field:SerializedName("gender")
    val gender: String? = null,

    @field:JsonProperty("dob")
    @field:SerializedName("dob")
    val dob: String? = null,

    @field:JsonProperty("dial_code")
    @field:SerializedName("dial_code")
    val dial_code: String? = null,

    @field:JsonProperty("father_name")
    @field:SerializedName("father_name")
    val father_name: String? = null,

    @field:JsonProperty("mother_name")
    @field:SerializedName("mother_name")
    val mother_name: String? = null,

    @field:JsonProperty("father_phone")
    @field:SerializedName("father_phone")
    val father_phone: String? = null,

    @field:JsonProperty("mother_phone")
    @field:SerializedName("mother_phone")
    val mother_phone: String? = null,

    @field:JsonProperty("parent_email")
    @field:SerializedName("parent_email")
    val parent_email: String? = null,

    @field:JsonProperty("local_guardian")
    @field:SerializedName("local_guardian")
    val local_guardian: String? = null,

    @field:JsonProperty("doj")
    @field:SerializedName("doj")
    val doj: String? = null,

    @field:JsonProperty("local_guardian_phone")
    @field:SerializedName("local_guardian_phone")
    val local_guardian_phone: String? = null,

    @field:JsonProperty("created_at")
    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:JsonProperty("class_teacher")
    @field:SerializedName("class_teacher")
    val class_teacher: String? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: ArrayList<SchoolResponse>? = null,

    @field:JsonProperty("hobbies")
    @field:SerializedName("hobbies")
    val hobbies: ArrayList<HobbiesResponse>? = null
    )
