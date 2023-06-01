package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Student;

import java.util.List;

public interface StudentService {
    MessageResponse saveStudent(Student student);

    MessageResponse saveListStudent(List<Student> student);

    MessageResponse getStudent(int id);

    MessageResponse getAllStudents();

    MessageResponse updateStudentById(Student student, int id);

    MessageResponse deleteStudentById(int id);

}
