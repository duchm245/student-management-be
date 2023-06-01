package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer>, JpaSpecificationExecutor<Class> {

    // get all class by course
    List<Class> findAllByIdIn(List<Integer> classIds);
//    List<Class> findAllByNameContainingIgnoreCase(String className);
    List<Class> findByNameContainingIgnoreCaseOrderByNameDesc(String className);

}