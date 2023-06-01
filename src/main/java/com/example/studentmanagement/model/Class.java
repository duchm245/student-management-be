package com.example.studentmanagement.model;

import com.example.studentmanagement.constants.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "number")
    private Integer number;

    @Column(name = "limit_class")
    private Integer limitClass;

    @Column(name = "type")
    private Integer type;

//    @Column(name = "status")
//    private Integer status;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "address", length = 250)
    private String address;

//    public String getStatus() {
//        if (status == 1) {
//            return "Hoat dong";
//        } else {
//            return "Khong hoat dong";
//        }
//    }

    public String getType() {
        if (type == 1) {
            return "Offline";
        } else {
            return "Online";
        }
    }

}