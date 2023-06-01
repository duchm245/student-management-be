package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "class_semester")
public class ClassSemester {
    @Id
    @Column(name = "class_semester_id", nullable = false)
    private Integer id;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "semester_id")
    private Integer semesterId;

}