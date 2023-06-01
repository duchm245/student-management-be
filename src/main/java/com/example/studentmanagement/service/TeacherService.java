package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.Teacher;

import java.util.List;

public interface TeacherService {
    MessageResponse saveTeacher(Teacher teacher);

    MessageResponse getTeacher(int id);

    MessageResponse getAllTeacher();

    MessageResponse updateTeacherById(Teacher teacher, int id);

    MessageResponse deleteTeacherById(int id);
}
