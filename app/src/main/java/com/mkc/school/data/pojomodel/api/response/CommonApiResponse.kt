package com.mkc.school.data.pojomodel.api.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class CommonApiResponse (
        @field:JsonProperty("status")
        @field:SerializedName("status")
        val status: Int? = null,

        @field:JsonProperty("message")
        @field:SerializedName("message")
        val message: String? = null
        )