package com.mkc.school.data.pojomodel.api.response.announcement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class TeachersDetails(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("user_id")
    @field:SerializedName("user_id")
    val user_id: Int? = null,

    @field:JsonProperty("firstname")
    @field:SerializedName("firstname")
    val firstname: String? = null,

    @field:JsonProperty("lastname")
    @field:SerializedName("lastname")
    val lastname: String? = null
    )
