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
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "number_credits")
    private Integer numberCredits;

    @Column(name = "type")
    private Integer type;

    @Column(name = "description")
    private String description;

}