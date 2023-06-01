package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id", nullable = false)
    private Integer id;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "type", length = 50)
    private Integer type;

    @Column(name = "mark")
    private Float mark;

    @Column(name = "date")
    private LocalDate date;

}