package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.repository.ClassSemesterRepository;
import com.example.studentmanagement.service.ClassSemesterService;
import org.springframework.stereotype.Service;

@Service
public class ClassSemesterServiceImpl implements ClassSemesterService {
    private  final MessageResponse messageResponse;
    private final ClassSemesterRepository classSemesterRepository;

    public ClassSemesterServiceImpl (MessageResponse messageResponse, ClassSemesterRepository classSemesterRepository) {
        this.messageResponse = messageResponse;
        this.classSemesterRepository = classSemesterRepository;
    }






}
