package com.mkc.school.data.pojomodel.api.response.grade

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExamDetailsResponse(
    @field:JsonProperty("question_type")
    @field:SerializedName("question_type")
    val question_type: Int? = null,

    @field:JsonProperty("title")
    @field:SerializedName("title")
    val title: String? = null,

    @field:JsonProperty("description")
    @field:SerializedName("description")
    val description: String? = null,

    @field:JsonProperty("total_marks")
    @field:SerializedName("total_marks")
    val total_marks: Float? = null,

    @field:JsonProperty("subject__subject__subject")
    @field:SerializedName("subject__subject__subject")
    val subject__subject__subject: String? = null,

    )
