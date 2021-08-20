package com.mkc.school.ui.grade

import com.mkc.school.data.pojomodel.api.response.exam.ExamResponse
import com.mkc.school.data.pojomodel.api.response.grade.GradeResponse


interface GradeNavigator {
    fun successGradeResponse(gradeResponse: GradeResponse?)
    fun successExamResponse(examResponse: ExamResponse?)
    fun errorGradeResponse(throwable: Throwable?)
}
