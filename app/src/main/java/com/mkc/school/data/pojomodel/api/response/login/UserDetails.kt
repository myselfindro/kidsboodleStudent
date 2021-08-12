package com.mkc.school.data.pojomodel.api.response.login

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDetails(
    @field:JsonProperty("user_id")
    @field:SerializedName("user_id")
    val user_id: Int? = null,

    @field:JsonProperty("student_user_id")
    @field:SerializedName("student_user_id")
    val student_user_id: Int? = null,

    @field:JsonProperty("user")
    @field:SerializedName("user")
    val user: String? = null,

    @field:JsonProperty("school_name")
    @field:SerializedName("school_name")
    val school_name: String? = null,

    @field:JsonProperty("first_name")
    @field:SerializedName("first_name")
    val first_name: String? = null,

    @field:JsonProperty("last_name")
    @field:SerializedName("last_name")
    val last_name: String? = null,

    @field:JsonProperty("parent_email")
    @field:SerializedName("parent_email")
    val parent_email: String? = null,

    @field:JsonProperty("dob")
    @field:SerializedName("dob")
    val dob: String? = null,

    @field:JsonProperty("gender")
    @field:SerializedName("gender")
    val gender: String? = null,

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

    @field:JsonProperty("local_guardian")
    @field:SerializedName("local_guardian")
    val local_guardian: String? = null,

    @field:JsonProperty("local_guardian_phone")
    @field:SerializedName("local_guardian_phone")
    val local_guardian_phone: String? = null,

    @field:JsonProperty("address1")
    @field:SerializedName("address1")
    val address1: String? = null,

    @field:JsonProperty("address2")
    @field:SerializedName("address2")
    val address2: String? = null,

    @field:JsonProperty("country_code")
    @field:SerializedName("country_code")
    val country_code: String? = null,

    @field:JsonProperty("state_code")
    @field:SerializedName("state_code")
    val state_code: String? = null,

    @field:JsonProperty("city_code")
    @field:SerializedName("city_code")
    val city_code: String? = null,

    @field:JsonProperty("pin_code")
    @field:SerializedName("pin_code")
    val pin_code: String? = null,

    @field:JsonProperty("dial_code")
    @field:SerializedName("dial_code")
    val dial_code: String? = null,

    @field:JsonProperty("created_at")
    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:JsonProperty("updated_at")
    @field:SerializedName("updated_at")
    val updated_at: String? = null
)
