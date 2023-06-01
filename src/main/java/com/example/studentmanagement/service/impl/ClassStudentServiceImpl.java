package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.ClassStudent;
import com.example.studentmanagement.repository.ClassRepository;
import com.example.studentmanagement.repository.ClassStudentRepository;
import com.example.studentmanagement.service.ClassStudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ClassStudentServiceImpl implements ClassStudentService {
    private final ClassStudentRepository classStudentRepository;
    private final ClassRepository classRepository;

    public ClassStudentServiceImpl(ClassStudentRepository classStudentRepository, ClassRepository classRepository) {
        this.classStudentRepository = classStudentRepository;
        this.classRepository = classRepository;
    }

//    public MessageResponse saveClazzStudent(ClassStudent classStudent) {
//        classStudent.setStatus(1);
//        List<ClassStudent> classStudentList = classStudentRepository.findByStudentId(classStudent.getStudentId());
//        if (classStudentList.isEmpty()) {
//            ClassStudent classStudent1 = classStudentRepository.save(classStudent);
//            return new MessageResponse("Create a clazz-student successful", 200, classStudent1);
//        } else {
//            classStudentList.forEach(classStudent2 -> {
//                if (classStudent2.getStatus() != null && classStudent2.getStatus() == 1) {
//                    classStudent2.setStatus(0);
//                    classStudentRepository.save(classStudent2);
//                }
//                classStudentRepository.save(classStudent);
//            });
//            return new MessageResponse("Create a clazz-student successful", 200, classStudent);
//        }
//    }

    /**
     * Hàm này dùng để phân một học sinh vào một lớp tương ứng
     *
     * @param classStudent
     * @return MessageResponse chứa
     */
    public MessageResponse saveClazzStudent(ClassStudent classStudent) {
        List<ClassStudent> classStudents = classStudentRepository.findByStudentIdAndClassId(classStudent.getStudentId(), classStudent.getClassId());
        if (classStudents.isEmpty()) {
            classStudent.setStatus(1);
            ClassStudent clasStu = classStudentRepository.save(classStudent);
//            List<ClassStudent> classStudents1 = classStudentRepository.findByStudentIdAndClassIdIsNotAndStatus(classStudent.getStudentId(), classStudent.getClassId(), 1);
//            classStudents1.forEach(classStudent2 -> {
//                classStudent2.setStatus(0);
//                classStudentRepository.save(classStudent2);
//            });
            return new MessageResponse("Create a clazz-student successful", 200, clasStu);
        } else {
            ClassStudent classStudent0 = classStudents.get(0);
            classStudent0.setStatus(1);
            classStudentRepository.save(classStudent0);
            // Chuyển trạng thái của các bản ghi(có lớp khác classId được gửi xuống) về 0 (không hoạt động)
            // ví dụ khi lên lớp 12 thì đã hoàn thành xong chương trình lớp 10 và 11
            List<ClassStudent> classStudents1 = classStudentRepository.findByStudentIdAndClassIdIsNotAndStatus(classStudent.getStudentId(), classStudent.getClassId(), 1);
            classStudents1.forEach(classStudent2 -> {
                classStudent2.setStatus(0);
                classStudentRepository.save(classStudent2);
            });
            return new MessageResponse("Update a clazz-student successful", 200, classStudent0);
        }
    }

    /**
     * Hàm này dùng để lấy ra bản ghi từ bảng class_student theo studentId và status là đang hoạt động
     *
     * @param studentId
     * @return MessageResponse
     */
    public MessageResponse getClazzIdByStudentId(int studentId) {
        List<ClassStudent> classStudent = classStudentRepository.findByStudentIdAndStatus(studentId, 1);
        if (classStudent.isEmpty()) {
            return new MessageResponse("This student has not been assigned to any class yet", 200, null);
        }
        return new MessageResponse("Get clazzId success", 200, classStudent.get(0));
    }

}
