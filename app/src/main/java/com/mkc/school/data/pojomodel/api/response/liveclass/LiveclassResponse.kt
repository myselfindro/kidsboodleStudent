package com.mkc.school.data.pojomodel.api.response.liveclass

data class LiveclassResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>?=null
)