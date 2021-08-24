package com.mkc.school.data.pojomodel.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ForgotPasswordWithEmailRequest (
   /* @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    var auth_provider: String? = null,

    @field:JsonProperty("student_fname")
    @field:SerializedName("student_fname")
    var student_fname: String? = null,*/

    @field:JsonProperty("email_id")
    @field:SerializedName("email_id")
    var email_id: String? = null
        )