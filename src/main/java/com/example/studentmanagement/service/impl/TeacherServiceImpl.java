package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Teacher;
import com.example.studentmanagement.repository.TeacherRepository;
import com.example.studentmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    MessageResponse messageResponse;

    @Autowired
    TeacherRepository teacherRepository;

    /**
     * This function saves the information of a teacher to the database
     * @param teacher The Teacher object to be saved
     * @return A MessageResponse object containing information about the success
     * of the operation and the saved Teacher object.
     */
    @Override
    public MessageResponse saveTeacher(Teacher teacher) {
        // xử lý thêm điều kiện, yêu cầu nghiệp vụ khác ...
        Teacher teacher1 = teacherRepository.save(teacher);
        messageResponse.setMessage("Create teacher successful");
        messageResponse.setData(teacher1);
        return messageResponse;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MessageResponse getTeacher(int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            messageResponse.setStatus(400);
            messageResponse.setMessage("Teacher id does not exist");
            return messageResponse;
        }
        messageResponse.setMessage("Get a teacher successful");
        messageResponse.setData(teacher);
        return messageResponse;
    }

    /**
     * @return
     */
    @Override
    public MessageResponse getAllTeacher() {
        List<Teacher> teachers = teacherRepository.findAll();
        if (teachers.isEmpty()) {
            messageResponse.setStatus(400);
            messageResponse.setMessage("Teacher id does not exist");
            return messageResponse;
        }
        messageResponse.setMessage("Get a teacher successful");
        messageResponse.setData(teachers);
        return messageResponse;
    }

    /**
     * @param teacher
     * @param id
     * @return
     */
    @Override
    public MessageResponse updateTeacherById(Teacher teacher, int id) {
        Teacher teacherUpdate = teacherRepository.findById(id).orElse(null);
        if (teacherUpdate == null) {
            messageResponse.setStatus(400);
            messageResponse.setMessage("Teacher id does not exist");
            return messageResponse;
        }
        teacherUpdate.setName(teacher.getName());
        teacherUpdate.setCode(teacher.getCode());
        teacherUpdate.setBirthday(teacher.getBirthday());
        teacherUpdate.setEmail(teacher.getEmail());
        teacherUpdate.setPhone(teacher.getPhone());
        teacherUpdate.setSex(teacher.getSex());
        teacherUpdate.setAddress(teacher.getAddress());
        teacherUpdate.setStatus(teacher.getStatus());
        teacherRepository.save(teacherUpdate);

        messageResponse.setMessage("Update teacher successful");
        messageResponse.setData(teacherUpdate);
        return messageResponse;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MessageResponse deleteTeacherById(int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            messageResponse.setStatus(400);
            messageResponse.setMessage("TeacherId does not exist");
        } else {
            teacherRepository.deleteById(id);
            messageResponse.setMessage("Delete teacher successful");
        }
        return messageResponse;
    }
}
