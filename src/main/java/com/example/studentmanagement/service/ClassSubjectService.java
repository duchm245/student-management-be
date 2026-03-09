package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.dto.TimeLineDto2;

public interface ClassSubjectService {

    MessageResponse createTimeLine(TimeLineDto2 tl);

    MessageResponse getTimeLineByFilter(int classId);

    MessageResponse updateTimeLine(TimeLineDto2 tl);

    MessageResponse getTimeLineMonday(int clazzId);
}
