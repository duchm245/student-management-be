package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.dto.ScheduleExamDto;
import com.example.studentmanagement.model.ScheduleExam;
import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.repository.ScheduleExamRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.SheduleExamService;
import com.example.studentmanagement.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ScheduleExamServiceImpl implements SheduleExamService {
    private final ScheduleExamRepository scheduleExamRepository;
    private final SubjectRepository subjectRepository;

    public ScheduleExamServiceImpl(ScheduleExamRepository scheduleExamRepository, SubjectRepository subjectRepository) {
        this.scheduleExamRepository = scheduleExamRepository;
        this.subjectRepository = subjectRepository;
    }

    /**
     * Hàm này dùng để lấy ra thông tin lịch thi
     * @param clazzId
     * @return MessageResponse
     */
    @Override
    public MessageResponse getScheduleExam(int clazzId) {
        List<ScheduleExamDto> result = new ArrayList<>();

        List<ScheduleExam> scheduleExams = scheduleExamRepository.findAllByClassId(clazzId);
        if (scheduleExams.isEmpty()) {
            return new MessageResponse("fails", 400, null);
        }
        scheduleExams.forEach(item -> {
            ScheduleExamDto scheduleExamDto = new ScheduleExamDto();
            Optional<Subject> subject = subjectRepository.findById(item.getSubjectId());
            subject.ifPresent(sub -> {
                scheduleExamDto.setSubjectName(sub.getName());
            });
            scheduleExamDto.setDate(DateTimeUtil.dateToString(item.getDay(), "dd/MM/yyyy"));
            scheduleExamDto.setLesson(item.getLesson());
            scheduleExamDto.setAddress(item.getAddress());
            result.add(scheduleExamDto);
        });
//        return new MessageResponse("get schedule exam success", 200, scheduleExams);
        return new MessageResponse("get schedule exam success", 200, result);
    }

}
