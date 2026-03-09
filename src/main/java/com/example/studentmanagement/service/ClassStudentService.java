package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.ClassStudent;

public interface ClassStudentService {

    MessageResponse saveClazzStudent(ClassStudent classStudent);

    MessageResponse getClazzIdByStudentId(int studentId);
}
