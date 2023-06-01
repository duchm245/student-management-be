package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Class;
import com.example.studentmanagement.model.ClassSubject;
import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.repository.ClassRepository;
import com.example.studentmanagement.repository.ClassSubjectRepository;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final ClassRepository classRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final SubjectRepository subjectRepository;

    /**
     * Hàm này dùng để lấy ra danh sách tất cả môn học
     * @return
     */
    public MessageResponse getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        return new MessageResponse("get all subject success", 200, subjects);
    }

    public MessageResponse getAllSubjectByClassId(int classId) {
        Optional<Class> classOptional = classRepository.findById(classId);
        if (classOptional.isEmpty()) {
            return new MessageResponse("Class khong ton tai", 400, null);
        }

        List<ClassSubject> classSubjectList = classSubjectRepository.findAllByClassId(classId);
        if (classSubjectList.isEmpty()) {
            return new MessageResponse("Subject khong ton tai", 400, null);
        }

        List<Integer> subjectIds = new ArrayList<>();
        classSubjectList.forEach(data -> {
            subjectIds.add(data.getSubjectId());
        });

        // Lấy ra danh sách subject theo list id
        List<Subject> subjects = subjectRepository.findAllByIdIn(subjectIds);

        return new MessageResponse("Get all subject by classId successful", 200, subjects);
    }


}
