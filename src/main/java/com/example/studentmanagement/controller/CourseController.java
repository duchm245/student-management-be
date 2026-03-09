package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.service.CourseService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/getAllCourse")
    public ResponseEntity<MessageResponse> getAllClazz() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }
}
