package com.example.studentmanagement.model;

import com.example.studentmanagement.constants.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private String email;

//    @OneToMany(mappedBy = "account")
//    @JsonBackReference
//    private Set<Role> roles = new HashSet<>();


}
