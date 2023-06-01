package com.example.studentmanagement.repository;

import com.example.studentmanagement.dto.StudentResponse;
import com.example.studentmanagement.model.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    Optional<Student> findStudentById(Integer id);

    List<Student> findAllByIdIn(List<Integer> studentIds);

//    List<Student> findAllByIdIn(List<Integer> studentIds, Pageable pageable);
    List<Student> findStudentByIdInAndLastNameContainingAndStatusAndSex(List<Integer> studentIds, String lastName, int status, int sex, Pageable pageable);


    @Query("SELECT new com.example.studentmanagement.dto.StudentResponse(s.firstName, s.lastName, c.name) " +
            "FROM Student s " +
            "JOIN ClassStudent cs ON s.id = cs.studentId " +
            "JOIN Class c ON cs.classId = c.id")
    List<StudentResponse> findStudentsWithClassName();

    List<Student> findAllByStatus(Integer status);
    List<Student> findAllByEmailIgnoreCase(String email);
}