package com.mkc.school.data.pojomodel.api.response.announcement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class AnnouncementDataResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("date")
    @field:SerializedName("date")
    val date: String? = null,

    @field:JsonProperty("to_school_or_class")
    @field:SerializedName("to_school_or_class")
    val to_school_or_class: Boolean? = null,

    @field:JsonProperty("scl_class")
    @field:SerializedName("scl_class")
    val scl_class: Int? = null,

    @field:JsonProperty("cls_section")
    @field:SerializedName("cls_section")
    val cls_section: Int? = null,

    @field:JsonProperty("title")
    @field:SerializedName("title")
    val title: String? = null,

    @field:JsonProperty("description")
    @field:SerializedName("description")
    val description: String? = null,

    @field:JsonProperty("announcement_type")
    @field:SerializedName("announcement_type")
    val announcement_type: String? = null,

    @field:JsonProperty("doc_path")
    @field:SerializedName("doc_path")
    val doc_path: String? = null,

    @field:JsonProperty("is_active")
    @field:SerializedName("is_active")
    val is_active: Boolean? = null,

    @field:JsonProperty("is_deleted")
    @field:SerializedName("is_deleted")
    val is_deleted: Boolean? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    @field:JsonProperty("school_user")
    @field:SerializedName("school_user")
    val school_user: Int? = null,

    @field:JsonProperty("teacher_details")
    @field:SerializedName("teacher_details")
    val teacher_details: TeachersDetails? = null
)
