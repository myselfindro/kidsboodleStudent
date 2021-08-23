package com.mkc.school.data.pojomodel.api.response.forgotpassword

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgotPasswordResponse(
    @field:JsonProperty("request_status")
    @field:SerializedName("request_status")
    val request_status: Int? = null,


    @field:JsonProperty("result")
    @field:SerializedName("result")
    val result: ForgotPasswordDataResponse? = null
)
