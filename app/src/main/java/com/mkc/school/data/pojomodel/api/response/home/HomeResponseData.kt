package com.mkc.school.data.pojomodel.api.response.home

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.profile.PerformanceDetails
import java.util.*
import kotlin.collections.ArrayList

@JsonIgnoreProperties(ignoreUnknown = true)
data class HomeResponseData(
    @field:JsonProperty("count_present")
    @field:SerializedName("count_present")
    val count_present: Int? = null,

    @field:JsonProperty("count_absent")
    @field:SerializedName("count_absent")
    val count_absent: Int? = null,

    @field:JsonProperty("total")
    @field:SerializedName("total")
    val total: Int? = null,

    @field:JsonProperty("present_percentage")
    @field:SerializedName("present_percentage")
    val present_percentage: Int? = null,

    @field:JsonProperty("announcement_count")
    @field:SerializedName("announcement_count")
    val announcement_count: Int? = null,

    @field:JsonProperty("lecture_count")
    @field:SerializedName("lecture_count")
    val lecture_count: Int? = null,

    @field:JsonProperty("exam_count")
    @field:SerializedName("exam_count")
    val exam_count: Int? = null,

    @field:JsonProperty("home_count")
    @field:SerializedName("home_count")
    val home_count: Int? = null,

    @field:JsonProperty("class_name")
    @field:SerializedName("class_name")
    val class_name: String? = null,

    @field:JsonProperty("section_name")
    @field:SerializedName("section_name")
    val section_name: String? = null,

    @field:JsonProperty("student_details")
    @field:SerializedName("student_details")
    val student_details: ArrayList<StudentDetails>? = null,

    @field:JsonProperty("school_details")
    @field:SerializedName("school_details")
    val school_details: ArrayList<SchoolDetails>? = null,

    @field:JsonProperty("announcement_list")
    @field:SerializedName("announcement_list")
    val announcement_list: ArrayList<AnnouncementList>? = null,

    @field:JsonProperty("get_performance_details")
    @field:SerializedName("get_performance_details")
//    val performance_details: PerformanceDetails? = null
    val performance_details: ArrayList<PerformanceDetails>? = null

)