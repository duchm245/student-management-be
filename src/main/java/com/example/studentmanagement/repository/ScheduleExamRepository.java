package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.ScheduleExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleExamRepository extends JpaRepository<ScheduleExam, Integer> {
    List<ScheduleExam> findAllByClassId(int clazzId);
}