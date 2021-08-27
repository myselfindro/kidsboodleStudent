package com.mkc.school.data.pojomodel.api.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
data class ChangePasswordRequest (
    @field:JsonProperty("auth_provider")
    @field:SerializedName("auth_provider")
    var auth_provider: String? = null,

    @field:JsonProperty("old_password")
    @field:SerializedName("old_password")
    var old_password: String? = null,

    @field:JsonProperty("new_password")
    @field:SerializedName("new_password")
    var new_password: String? = null
        )