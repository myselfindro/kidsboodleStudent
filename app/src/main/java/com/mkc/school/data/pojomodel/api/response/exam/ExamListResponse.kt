package com.mkc.school.data.pojomodel.api.response.exam

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExamListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("exam_type")
    @field:SerializedName("exam_type")
    val exam_type: String? = null,

    @field:JsonProperty("is_active")
    @field:SerializedName("is_active")
    val is_active: Boolean? = null,

    @field:JsonProperty("is_deleted")
    @field:SerializedName("is_deleted")
    val is_deleted: Boolean? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    )
