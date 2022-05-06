package com.mkc.school.ui.grade;

import java.util.List;

public class GradeModel{
    private String exam_type_name, examid;
    private List<SubjectModel> subjectModelList;

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getExam_type_name() {
        return exam_type_name;
    }

    public void setExam_type_name(String exam_type_name) {
        this.exam_type_name = exam_type_name;
    }

    public List<SubjectModel> getSubjectModelList() {
        return subjectModelList;
    }

    public void setSubjectModelList(List<SubjectModel> subjectModelList) {
        this.subjectModelList = subjectModelList;
    }
}
