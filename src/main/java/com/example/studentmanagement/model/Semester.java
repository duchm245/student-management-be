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
@Table(name = "semester")
public class Semester {
    @Id
    @Column(name = "semester_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "main")
    private Integer main;

    @Column(name = "extra_evening")
    private Integer extraEvening;

    @Column(name = "extra_summer")
    private Integer extraSummer;

    @Column(name = "status")
    private Integer status;

}