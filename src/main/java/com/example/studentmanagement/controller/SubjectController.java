package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.service.impl.SubjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class SubjectController {
    private final SubjectServiceImpl subjectService;

    @GetMapping("/getAllSubject")
    public ResponseEntity<MessageResponse> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAllSubject());
    }
    @GetMapping("getAllSubjectByClassId")
    public ResponseEntity<MessageResponse> getAllSubjectByClassId(@Param("classId") int classId) {
        return ResponseEntity.ok(subjectService.getAllSubjectByClassId(classId));
    }



}
