package com.mkc.school.data.pojomodel.api.response.profile

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class SchoolResponse(
    @field:JsonProperty("id")
    @field:SerializedName("id")
    val id: Int? = null,

    @field:JsonProperty("school_name")
    @field:SerializedName("school_name")
    val school_name: String? = null,

    @field:JsonProperty("reg_no")
    @field:SerializedName("reg_no")
    val reg_no: String? = null,

    @field:JsonProperty("school_email")
    @field:SerializedName("school_email")
    val school_email: String? = null,

    @field:JsonProperty("school_phone1")
    @field:SerializedName("school_phone1")
    val school_phone1: String? = null,

    @field:JsonProperty("school_phone2")
    @field:SerializedName("school_phone2")
    val school_phone2: String? = null

    )
