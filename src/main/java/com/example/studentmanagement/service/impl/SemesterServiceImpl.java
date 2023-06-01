package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Semester;
import com.example.studentmanagement.repository.SemesterRepository;
import com.example.studentmanagement.service.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;

    public MessageResponse getAllSemester() {
        List<Semester> semesterList = semesterRepository.findAll();
        return new MessageResponse("get successful", 200, semesterList);
    }
}
