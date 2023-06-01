package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "class_subject")
public class ClassSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_subject_id", nullable = false)
    private Integer id;
    @Column(name = "class_id")
    private int classId;
    @Column(name = "subject_id")
    private int subjectId;
    @Column(name = "day")
    private int day;
    @Column(name = "lesson")
    private int lesson;

    public ClassSubject(int classId, int subjectId, int day, int lesson) {
        this.classId = classId;
        this.subjectId = subjectId;
        this.day = day;
        this.lesson = lesson;
    }

    public ClassSubject() {

    }
}
