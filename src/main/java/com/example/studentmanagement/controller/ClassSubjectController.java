package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.dto.TimeLineDto2;
import com.example.studentmanagement.service.impl.ClassSubjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class ClassSubjectController {
    private final ClassSubjectServiceImpl classSubjectService;

    @PostMapping("/createTimeLine")
    public ResponseEntity<MessageResponse> createTimeLine(@RequestBody TimeLineDto2 tl2) {
        return ResponseEntity.ok(classSubjectService.createTimeLine(tl2));
    }

    @GetMapping("/getTimeLineByFilter")
    public ResponseEntity<MessageResponse> getTimeLineByFilter(@Param("classId") int classId) {
        return ResponseEntity.ok(classSubjectService.getTimeLineByFilter(classId));
    }

    @GetMapping("/getTimeLineMonday")
    public ResponseEntity<MessageResponse> getTimeLineDay(@Param("clazzId") int clazzId) {
        return ResponseEntity.ok(classSubjectService.getTimeLineMonday(clazzId));
    }

    @PutMapping("/updateTimeLine")
    public ResponseEntity<MessageResponse> updateTimeLine(@RequestBody TimeLineDto2 tl2) {
        return ResponseEntity.ok(classSubjectService.updateTimeLine(tl2));
    }
}
