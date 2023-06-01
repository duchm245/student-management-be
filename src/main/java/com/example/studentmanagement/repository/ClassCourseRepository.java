package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.ClassCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassCourseRepository extends JpaRepository<ClassCourse, Integer> {
    List<ClassCourse> findAllByCourseId(int sourceId);
}