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
@Table(name = "subject_semester")
public class SubjectSemester {
    @Id
    @Column(name = "subject_semester_id", nullable = false)
    private Integer id;

    @Column(name = "semester_id")
    private Integer semesterId;

    @Column(name = "subject_id")
    private Integer subjectId;

}