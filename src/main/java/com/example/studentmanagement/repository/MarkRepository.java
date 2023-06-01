package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Class;
import com.example.studentmanagement.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer>, JpaSpecificationExecutor<Mark> {
}