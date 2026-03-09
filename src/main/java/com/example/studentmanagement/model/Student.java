package com.example.studentmanagement.model;

import com.example.studentmanagement.constants.Gender;
import com.example.studentmanagement.constants.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "First name must not be blank")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @Column(name = "last_name", length = 50)
    @NotBlank(message = "Last name must not be blank")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @Column(name = "code", length = 50)
    @NotBlank(message = "Code must not be blank")
    @Size(max = 50, message = "Code must be at most 50 characters")
    private String code;

    @Column(name = "teacher_major_id")
    private Integer teacherMajorId;

    @Column(name = "class_main", length = 50)
    @Size(max = 50, message = "Class name must be at most 50 characters")
    private String classMain;

    @Column(name = "sex")
    @JsonProperty("gender")
    @NotNull(message = "Gender must not be null")
    private Gender sex;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = "email", length = 50)
    @Email(message = "Email must be valid")
    @Size(max = 50, message = "Email must be at most 50 characters")
    private String email;

    @Column(name = "phone", length = 50)
    @Size(max = 50, message = "Phone must be at most 50 characters")
    private String phone;

    @Column(name = "address", length = 50)
    private String address;

    //    @Column(name = "status")
//    private Integer status;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Status must not be null")
    private Status status;

//    public String getSex() {
//        if (sex == 1) {
//            return "Nam";
//        } else {
//            return "Nữ";
//        }
//    }
}