package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public MessageResponse getAllCourse() {
        List<?> courseList = courseRepository.findAll();
        return new MessageResponse("okk", 200, courseList);
    }
}
