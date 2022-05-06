package com.mkc.school.data.pojomodel.api.response.liveclass

data class TimeSlotDetails(
    val created_at: String,
    val created_by: Any,
    val end_time: String,
    val id: Int,
    val is_active: Boolean,
    val is_break: Boolean,
    val is_deleted: Boolean,
    val owned_by: Any,
    val period_name: String,
    val school: Int,
    val start_time: String,
    val updated_at: String,
    val updated_by: Any
)