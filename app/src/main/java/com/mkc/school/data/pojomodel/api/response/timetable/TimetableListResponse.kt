package com.mkc.school.data.pojomodel.api.response.timetable

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class TimetableListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    @field:JsonProperty("scl_class")
    @field:SerializedName("scl_class")
    val scl_class: Int? = null,

    @field:JsonProperty("cls_section")
    @field:SerializedName("cls_section")
    val cls_section: Int? = null,

    @field:JsonProperty("school_user")
    @field:SerializedName("school_user")
    val school_user: Int? = null,

    @field:JsonProperty("start_time")
    @field:SerializedName("start_time")
    val start_time: String? = null,

    @field:JsonProperty("end_time")
    @field:SerializedName("end_time")
    val end_time: String? = null,

    @field:JsonProperty("day_name")
    @field:SerializedName("day_name")
    val day_name: String? = null,

    @field:JsonProperty("routine_date")
    @field:SerializedName("routine_date")
    val routine_date: String? = null,

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

    @field:JsonProperty("subject")
    @field:SerializedName("subject")
    val subject: String? = null,

    @field:JsonProperty("created_by")
    @field:SerializedName("created_by")
    val created_by: String? = null,

    @field:JsonProperty("owned_by")
    @field:SerializedName("owned_by")
    val owned_by: String? = null,

    @field:JsonProperty("updated_by")
    @field:SerializedName("updated_by")
    val updated_by: String? = null,

    @field:JsonProperty("class_name")
    @field:SerializedName("class_name")
    val class_name: String? = null,

    @field:JsonProperty("section")
    @field:SerializedName("section")
    val section: String? = null,

    @field:JsonProperty("teacher")
    @field:SerializedName("teacher")
    val teacher: String? = null
)
