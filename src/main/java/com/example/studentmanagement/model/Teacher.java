package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "status")
    private Integer status;

}