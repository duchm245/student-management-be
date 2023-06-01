package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.SubjectSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectSemesterRepository extends JpaRepository<SubjectSemester, Integer> {
}