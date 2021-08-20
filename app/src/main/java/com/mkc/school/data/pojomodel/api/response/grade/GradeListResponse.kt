package com.mkc.school.data.pojomodel.api.response.grade

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class GradeListResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("total_marks_obtained")
    @field:SerializedName("total_marks_obtained")
    val total_marks_obtained: Float? = null,

    @field:JsonProperty("result_publish_date")
    @field:SerializedName("result_publish_date")
    val result_publish_date: String? = null,

    @field:JsonProperty("is_active")
    @field:SerializedName("is_active")
    val is_active: Boolean? = null,

    @field:JsonProperty("is_deleted")
    @field:SerializedName("is_deleted")
    val is_deleted: Boolean? = null,

    @field:JsonProperty("student")
    @field:SerializedName("student")
    val student: Int? = null,

    @field:JsonProperty("exam")
    @field:SerializedName("exam")
    val exam: Int? = null,

    @field:JsonProperty("exam_grade")
    @field:SerializedName("exam_grade")
    val exam_grade: Int? = null,

    @field:JsonProperty("exam_details")
    @field:SerializedName("exam_details")
    val exam_details: ArrayList<ExamDetailsResponse>? = null,

    @field:JsonProperty("grade_details")
    @field:SerializedName("grade_details")
    val grade_details: ArrayList<GradeDetailsResponse>? = null
)
