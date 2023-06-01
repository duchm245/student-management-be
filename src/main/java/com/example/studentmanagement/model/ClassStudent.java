package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "class_student")
public class ClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_student_id", nullable = false)
    private Integer id;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "status")
    private Integer status;

}