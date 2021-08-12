package com.mkc.school.data.pojomodel.api.response.home

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class StudentDetails(

    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("school")
    @field:SerializedName("school")
    val school: Int? = null,

    @field:JsonProperty("student_fname")
    @field:SerializedName("student_fname")
    val student_fname: String? = null,

    @field:JsonProperty("student_lname")
    @field:SerializedName("student_lname")
    val student_lname: String? = null,
)
