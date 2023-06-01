package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MessageResponse;

public interface SheduleExamService {
    MessageResponse getScheduleExam(int clazzId);
}
