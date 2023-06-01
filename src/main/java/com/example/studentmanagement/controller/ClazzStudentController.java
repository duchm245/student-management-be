package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.ClassStudent;
import com.example.studentmanagement.service.impl.ClassStudentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
//@CrossOrigin
public class ClazzStudentController {
    private final ClassStudentServiceImpl classStudentService;

    public ClazzStudentController(ClassStudentServiceImpl classStudentService) {
        this.classStudentService = classStudentService;
    }

    /**
     * Hàm này dùng để phân lớp cho một học sinh
     * @param classStudent
     * @return
     */
    @PostMapping("/createClazzStudent")
    public ResponseEntity<MessageResponse> saveClazzStudent(@RequestBody ClassStudent classStudent) {
        return ResponseEntity.ok(classStudentService.saveClazzStudent(classStudent));
    }

    /**
     *
     * @param studentId
     * @return
     */
    @GetMapping("/getClazzIdByStudentId")
    public ResponseEntity<MessageResponse> getClazzIdByStudentId(@RequestParam("studentId") int studentId) {
        return ResponseEntity.ok(classStudentService.getClazzIdByStudentId(studentId));
    }


}
