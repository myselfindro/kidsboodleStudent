package com.mkc.school.data.pojomodel.api.response.profile

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class PerformanceDetails(
    @field:JsonProperty("min_marks")
    @field:SerializedName("min_marks")
    val min_marks: Int? = null,

    @field:JsonProperty("max_marks")
    @field:SerializedName("max_marks")
    val max_marks: Int? = null,

    @field:JsonProperty("star")
    @field:SerializedName("star")
    val star: Int? = null,

    @field:JsonProperty("performance")
    @field:SerializedName("performance")
    val performance: String? = null
)
