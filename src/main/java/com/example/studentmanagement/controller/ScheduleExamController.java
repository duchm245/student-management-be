package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.service.impl.ScheduleExamServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ScheduleExamController {
    private final ScheduleExamServiceImpl scheduleExamService;

    public ScheduleExamController(ScheduleExamServiceImpl scheduleExamService) {
        this.scheduleExamService = scheduleExamService;
    }

    @GetMapping("/getScheduleExam")
    public ResponseEntity<MessageResponse> getScheduleExam(@Param("clazzId") int clazzId) {
        return ResponseEntity.ok(scheduleExamService.getScheduleExam(clazzId));
    }
}
