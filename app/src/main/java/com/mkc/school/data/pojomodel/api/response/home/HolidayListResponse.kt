package com.mkc.school.data.pojomodel.api.response.home

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class HolidayListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("date")
    @field:SerializedName("date")
    val date: String? = null,

    @field:JsonProperty("day_name")
    @field:SerializedName("day_name")
    val day_name: String? = null,

    @field:JsonProperty("leave_name")
    @field:SerializedName("leave_name")
    val leave_name: String? = null,

    @field:JsonProperty("leave_type")
    @field:SerializedName("leave_type")
    val leave_type: String? = null,

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

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    @field:JsonProperty("session")
    @field:SerializedName("session")
    val session: Int? = null,

    @field:JsonProperty("created_by")
    @field:SerializedName("created_by")
    val created_by: String? = null,

    @field:JsonProperty("owned_by")
    @field:SerializedName("owned_by")
    val owned_by: String? = null,

    @field:JsonProperty("updated_by")
    @field:SerializedName("updated_by")
    val updated_by: String? = null
)
