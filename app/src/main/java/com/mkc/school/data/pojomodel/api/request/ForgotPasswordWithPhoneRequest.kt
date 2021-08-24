package com.mkc.school.data.pojomodel.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgotPasswordWithPhoneRequest (
 /*   @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    var auth_provider: String? = null,

    @field:JsonProperty("student_fname")
    @field:SerializedName("student_fname")
    var student_fname: String? = null,*/

    @field:JsonProperty("phone_no")
    @field:SerializedName("phone_no")
    var phone_no: String? = null,

    @field:JsonProperty("dial_code")
    @field:SerializedName("dial_code")
    var dial_code: String? = null
        )