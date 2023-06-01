package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.SubjectLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectLessonRepository extends JpaRepository<SubjectLesson, Integer> {
}