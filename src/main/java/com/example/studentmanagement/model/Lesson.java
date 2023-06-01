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
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "lesson_id", nullable = false)
    private Integer id;

    @Column(name = "lesson")
    private Integer lesson;

}