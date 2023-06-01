package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ClazzDto;
import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Class;

public interface ClassService {
    MessageResponse saveClazz(Class clazz);

    MessageResponse getAllClazzByCourseId(int courseId);

    MessageResponse updateClazzById(ClazzDto clazzDto);

    MessageResponse deleteClazzById(int id);
}
