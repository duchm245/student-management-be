package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.service.impl.SemesterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class SemesterController {
    private final SemesterServiceImpl semesterService;

    public SemesterController(SemesterServiceImpl semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping("/getAllSemester")
    public ResponseEntity<MessageResponse> getAllSemester() {
        return ResponseEntity.ok(semesterService.getAllSemester());
    }
}
