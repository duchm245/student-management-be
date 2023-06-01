package com.example.studentmanagement.constants;

import lombok.Data;

@Data
public class ApiDataItem {
    private int id;
    private int classId;
    private int subjectId;
    private int semester;
    private String lesson;
    private String day;
    private int lessonExam;
    private String scheduleExam;
}
