package com.mkc.school.data.pojomodel.api.response.teachers

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeacherSubject(
    @field:JsonProperty("subject")
    @field:SerializedName("subject")
    val subject: String? = null
)
