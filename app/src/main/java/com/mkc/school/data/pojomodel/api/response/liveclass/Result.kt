package com.mkc.school.data.pojomodel.api.response.liveclass

data class Result(
    val class_link: String,
    val cls_section: Int,
    val created_at: String,
    val created_by: Any,
    val date: String,
    val id: Int,
    val is_active: Boolean,
    val is_deleted: Boolean,
    val is_upcoming: Boolean,
    val owned_by: Any,
    val routine: Routine,
    val school: Any,
    val updated_at: String,
    val updated_by: Any
)