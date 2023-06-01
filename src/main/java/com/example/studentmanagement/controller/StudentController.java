package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.impl.StudentServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
//@CrossOrigin
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    /**
     * Hàm này để tạo mới một student
     *
     * @param student
     * @return MessageResponse chứa student vừa tạo
     */
    @PostMapping("/createStudent")
    public ResponseEntity<MessageResponse> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

//    @PostMapping("/createListStudent")
//    public ResponseEntity<?> saveListStudent(@RequestBody List<Student> studentList) {
//        return ResponseEntity.ok(studentService.saveListStudent(studentList));
//    }

    /**
     * Hàm này dùng để lấy ra một student theo id
     *
     * @param id
     * @return MessageResponse chứa một student
     */
    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<MessageResponse> getStudent(@PathVariable("studentId") int id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

//    @GetMapping("/getAllStudents")
//    public ResponseEntity<MessageResponse> getAllStudent() {
//        return ResponseEntity.ok(studentService.getAllStudents());
//    }

//    @GetMapping("/getStudentNameAndClassname")
//    public ResponseEntity<MessageResponse> getStudentNameAndClassname() {
//        return ResponseEntity.ok(studentService.getStudentNameAndClassname());
//    }

//    @GetMapping("/getAllStudentsByCourseIdAndClassId")
//    public ResponseEntity<MessageResponse> getAllStudentsByCourseIdAndClassId(@Param("classId") int classId) {
//        return ResponseEntity.ok(studentService.getAllStudentsByCourseIdAndClassId(classId));
//    }

//    @GetMapping("/getAllStudentByStatus")
//    public ResponseEntity<MessageResponse> getAllStudentByStatus(@Param("status") int status) {
//        return ResponseEntity.ok(studentService.getAllStudentByStatus(status));
//    }

    /**
     * Hàm này dùng để lấy ra danh sách student theo bộ lọc
     *
     * @param classId
     * @param gender
     * @param status
     * @param name
     * @param sortSelected
     * @param page
     * @param pageSize
     * @return MessageResponse
     */
    @GetMapping("/getAllStudentsByFilter")
    public ResponseEntity<MessageResponse> getAllStudentByFilter(@Param("classId") int classId,
                                                                 @Param("gender") int gender,
                                                                 @Param("status") int status,
                                                                 @Param("name") String name,
                                                                 @Param("sortSelected") String sortSelected,
                                                                 @Param("page") int page,
                                                                 @Param("size") int pageSize
    ) {
        // y tuong
//        Map<String, Object> filters = new HashMap<>();
//        filters.put("name", name);
//        filters.put("status", status);
//        filters.put("gender", gender);
        return ResponseEntity.ok(studentService.getAllStudentByFilter(classId, gender, status, name, sortSelected, page, pageSize));
    }

    /**
     * Hàm này dùng để update một học sinh
     *
     * @param student
     * @param id
     */
    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<MessageResponse> updateStudentById(@RequestBody Student student, @PathVariable("studentId") int id) {
        return ResponseEntity.ok(studentService.updateStudentById(student, id));
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<MessageResponse> deleteStudentById(@PathVariable("studentId") int id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    /**
     * Hàm này sẽ xóa mềm, tức là chỉ thay đổi trạng thái về : không hoạt động
     *
     * @param id
     */
    @PostMapping("/deleteSoftStudent/{studentId}")
    public ResponseEntity<MessageResponse> deleteSoftStudentById(@PathVariable("studentId") int id) {
        return ResponseEntity.ok(studentService.deleteSoftStudentById(id));
    }

    /**
     * Hàm này dùng để kiểm tra một email đã tồn tại chưa
     *
     * @param email
     * @return MessageResponse chứa giá trị true: tồn tại, false: chưa tồn tại
     */
    @GetMapping("/checkEmailExist/{email}")
    public ResponseEntity<MessageResponse> checkEmailExist(@PathVariable("email") String email) {
        return ResponseEntity.ok(studentService.checkEmailExist(email));
    }


}
