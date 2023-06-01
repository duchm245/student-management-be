package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassStudentDto {
    private int studentId;
    private int classId;
    private String className;
    private String studentCode;
    private String studentName;
    private String studentGender;
    private String studentBirthday;
    private String studentEmail;
    private String studentPhone ;
    private String studentStatus;
}

