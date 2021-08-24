package com.mkc.school.data.pojomodel.api.response.forgotpassword

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgotPasswordDataResponse(
    @field:JsonProperty("dial_code")
    @field:SerializedName("dial_code")
    val dial_code: String? = null,

    @field:JsonProperty("Phone_number")
    @field:SerializedName("Phone_number")
    val Phone_number: String? = null,

    @field:JsonProperty("email_id")
    @field:SerializedName("email_id")
    val email_id: String? = null,

    @field:JsonProperty("otp")
    @field:SerializedName("otp")
    val otp: String? = null

)
