package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Majoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajoringRepository extends JpaRepository<Majoring, Integer> {
}