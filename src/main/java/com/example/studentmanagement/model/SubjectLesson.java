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
@Table(name = "subject_lesson")
public class SubjectLesson {
    @Id
    @Column(name = "subject_lesson_id", nullable = false)
    private Integer id;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "subject_id")
    private Integer subjectId;

}