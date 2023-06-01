package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.ClassSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSemesterRepository extends JpaRepository<ClassSemester, Integer> {

    List<ClassSemester> findAllBySemesterId(int semesterId);
}