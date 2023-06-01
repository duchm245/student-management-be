package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.MessageResponse;
import com.example.studentmanagement.service.impl.MarkServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MarkController {
    private final MarkServiceImpl markService;

    public MarkController(MarkServiceImpl markService) {
        this.markService = markService;
    }

//    @GetMapping("/getMarkByFiler")
//    public ResponseEntity<MessageResponse> getMarkByFiler(@Param("clazzId") int clazzId,
//                                                          @Param("subjectId") int subjectId,
//                                                          @Param("studentId") int studentId) {
//        return ResponseEntity.ok(markService.getMarkByFilter(clazzId, subjectId, studentId));
//    }

    /**
     * Hàm này dùng để xem điểm theo danh sách môn học của một học sinh
     * @param clazzId
     * @param studentId
     * @return MessageResponse
     */
    @GetMapping("/getMarkByFilter2")
    public ResponseEntity<MessageResponse> getMarkByFiler2(@Param("clazzId") int clazzId,
                                                          @Param("studentId") int studentId) {
        return ResponseEntity.ok(markService.getMarkByFilter2(clazzId, studentId));
    }

    /**
     * Hàm này dùng để xemd điểm của một lớp theo từng môn
     * @param clazzId
     * @param subjectId
     * @return
     */
    @GetMapping("/getMarkByFiler3")
    public ResponseEntity<MessageResponse> getMarkByFiler3(@Param("clazzId") int clazzId,
                                                           @Param("subjectId") int subjectId) {
        return ResponseEntity.ok(markService.getMarkByFilter3(clazzId, subjectId));
    }

}
