package com.mkc.school.data.pojomodel.api.response.home

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class AnnouncementList(

    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("date")
    @field:SerializedName("date")
    val date: String? = null,

    @field:JsonProperty("title")
    @field:SerializedName("title")
    val title: String? = null,

    @field:JsonProperty("description")
    @field:SerializedName("description")
    val description: String? = null
)
