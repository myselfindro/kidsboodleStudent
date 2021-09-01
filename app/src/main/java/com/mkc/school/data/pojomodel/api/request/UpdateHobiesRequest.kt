package com.mkc.school.data.pojomodel.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class UpdateHobiesRequest (
 /*   @field:JsonProperty("hobby_id")
    @field:SerializedName("hobby_id")
    var hobby_id: String? = null,

    @field:JsonProperty("method")
    @field:SerializedName("method")
    var method: String? = null,*/

    @field:JsonProperty("hobby")
    @field:SerializedName("hobby")
    var hobby: String? = null
        )