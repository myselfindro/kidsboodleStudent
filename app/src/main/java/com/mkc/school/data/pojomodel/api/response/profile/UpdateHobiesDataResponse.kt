package com.mkc.school.data.pojomodel.api.response.profile

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpdateHobiesDataResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null
)
