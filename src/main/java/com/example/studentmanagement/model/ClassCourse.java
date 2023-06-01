package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "class_course")
public class ClassCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_course_id", nullable = false)
    private Integer id;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "course_id")
    private Integer courseId;

}