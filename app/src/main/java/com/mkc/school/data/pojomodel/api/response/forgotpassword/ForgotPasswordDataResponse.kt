package com.mkc.school.data.pojomodel.api.response.forgotpassword

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgotPasswordDataResponse(
    @field:JsonProperty("msg")
    @field:SerializedName("msg")
    val msg: String? = null

    )
