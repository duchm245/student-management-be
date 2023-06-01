package com.example.studentmanagement.model;

import com.example.studentmanagement.constants.Gender;
import com.example.studentmanagement.constants.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "teacher_major_id")
    private Integer teacherMajorId;

    @Column(name = "class_main", length = 50)
    private String classMain;

//    @Column(name = "sex")
//    private Integer sex;
    @Column(name = "sex")
    private Gender sex;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "address", length = 50)
    private String address;

    //    @Column(name = "status")
//    private Integer status;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

//    public String getSex() {
//        if (sex == 1) {
//            return "Nam";
//        } else {
//            return "Nữ";
//        }
//    }
}