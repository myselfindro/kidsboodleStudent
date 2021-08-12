package com.mkc.school.data.pojomodel.api.response.login

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponseData(

    @field:JsonProperty("token")
    @field:SerializedName("token")
    val token: String? = null,

    @field:JsonProperty("token_expiry")
    @field:SerializedName("token_expiry")
    val token_expiry: String? = null,

    @field:JsonProperty("username")
    @field:SerializedName("username")
    val username: String? = null,

    @field:JsonProperty("user_details")
    @field:SerializedName("user_details")
    val user_details: UserDetails? = null,
)
