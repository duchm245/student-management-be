package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ClazzDto;
import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.model.Class;
import com.example.studentmanagement.service.impl.ClassServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ClazzController {
    private final ClassServiceImpl clazzService;

    public ClazzController(ClassServiceImpl clazzService) {
        this.clazzService = clazzService;
    }

    @PostMapping("/createClazz")
    public ResponseEntity<MessageResponse> saveClazz(@RequestBody Class clazz) {
        return ResponseEntity.ok(clazzService.saveClazz(clazz));
    }

    @GetMapping("/getAllClazz")
    public ResponseEntity<MessageResponse> getAllClazz() {
        return ResponseEntity.ok(clazzService.getAllClazz());
    }

    @GetMapping("/getClassById/{classId}")
    public ResponseEntity<MessageResponse> getClassById(@PathVariable("classId") int classId) {
        return ResponseEntity.ok(clazzService.getClassById(classId));
    }

    @GetMapping("/getAllClazzByFilter")
    public ResponseEntity<MessageResponse> getAllClazzByFilter(@Param("name") String name,
                                                               @Param("type") int type,
                                                               @Param("status") int status,
                                                               @Param("sortSelected") String sortSelected,
                                                               @Param("page") int page,
                                                               @Param("pageSize") int pageSize) {
        return ResponseEntity.ok(clazzService.getAllClazzByFilter(name, type, status, sortSelected, page, pageSize));
    }

    @GetMapping("/getAllClazzByCourseId")
    public ResponseEntity<MessageResponse> getAllClazzByCourseId(@Param("courseId") int courseId) {
        return ResponseEntity.ok(clazzService.getAllClazzByCourseId(courseId));
    }

    @GetMapping("/getAllClazzBySemesterId")
    public ResponseEntity<MessageResponse> getAllClazzBySemester(@Param("semesterId") int semesterId) {
        return ResponseEntity.ok(clazzService.getAllClazzBySemesterId(semesterId));
    }

    @GetMapping("getInfoSubjectAndClass")
    public ResponseEntity<MessageResponse> getInfoSubjectAndClass(@Param("classId") int classId) {
        return ResponseEntity.ok(clazzService.getInfoSubjectAndClass(classId));
    }

    @GetMapping("getAllClazzByFilterLikeClassName")
    public ResponseEntity<MessageResponse> getAllClazzByFilterLikeClassName(@Param("className") String className) {
        return ResponseEntity.ok(clazzService.getAllClazzByFilterLikeClassName(className));
    }

    @GetMapping("getInfoClassAndStudent")
    public ResponseEntity<MessageResponse> getInfoClassAndStudent() {
        return ResponseEntity.ok(clazzService.getInfoClassAndStudent2());
    }

    @PutMapping("/updateClazz")
    public ResponseEntity<MessageResponse> updateClazzById(@RequestBody ClazzDto clazzDto) {
        return ResponseEntity.ok(clazzService.updateClazzById(clazzDto));
    }

    @DeleteMapping("/deleteClazz/{clazzId}")
    public ResponseEntity<MessageResponse> deleteClazzById(@PathVariable("clazzId") int id) {
        return ResponseEntity.ok(clazzService.deleteClazzById(id));
    }

    @PostMapping("/deleteSoftClazz/{clazzId}")
    public ResponseEntity<MessageResponse> deleteSoftClazz(@PathVariable("clazzId") int id) {
        return ResponseEntity.ok(clazzService.deleteSoftClazzById(id));
    }
}
