package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedule_exam")
public class ScheduleExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_exam_id", nullable = false)
    private Integer id;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "day")
    private Date day;

    @Column(name = "lesson")
    private Integer lesson;

    @Column(name = "address", length = 50)
    private String address;

}