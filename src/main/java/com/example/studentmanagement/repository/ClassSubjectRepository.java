package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.ClassSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassSubjectRepository extends JpaRepository<ClassSubject, Integer> {

    List<ClassSubject> findAllByClassId(int classId);
//    List<ClassSubject> findAllByClassIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int classId, Date startDate, Date endDate);
    List<ClassSubject> findAllByLesson(int lesson);

    List<ClassSubject> findAllByClassIdOrderByIdDesc(int classId);

    Optional<ClassSubject> findByClassIdAndDayAndLesson(int classId, int day, int lesson);

    List<ClassSubject> findAllByClassIdAndDay(int clazzId, int day);
}