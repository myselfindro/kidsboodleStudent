package com.mkc.school.data.pojomodel.api.response.profile

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class HobbiesResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("hobby")
    @field:SerializedName("hobby")
    val hobby: String? = null
)
