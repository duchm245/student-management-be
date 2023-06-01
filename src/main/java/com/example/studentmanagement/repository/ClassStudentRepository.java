package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent, Integer> {
    List<ClassStudent> findByStudentId(int studentId);
    List<ClassStudent> findAllByClassId(int classId);
    // select * from studentclass order by id desc
    List<ClassStudent> findAllByStudentIdOrderByIdDesc(int studentId);
    List<ClassStudent> findByStudentIdAndStatus(int studentId, int status);


    List<ClassStudent> findByStudentIdAndClassId(int studentId, int classId);
    List<ClassStudent> findByStudentIdAndClassIdIsNotAndStatus(int studentId, int classId, int status);
}