package com.mkc.school.data.pojomodel.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ChangePasswordWithEmailRequest (
    @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    var auth_provider: String? = null,

    @field:JsonProperty("username")
    @field:SerializedName("username")
    var username: String? = null,

    @field:JsonProperty("new_password")
    @field:SerializedName("new_password")
    var new_password: String? = null,

    @field:JsonProperty("confirm_password")
    @field:SerializedName("confirm_password")
    var confirm_password: String? = null,

    @field:JsonProperty("email")
    @field:SerializedName("email")
    var email: String? = null
        )