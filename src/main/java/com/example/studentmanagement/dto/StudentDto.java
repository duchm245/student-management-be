package com.example.studentmanagement.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.studentmanagement.model.Student} entity
 */
@Data
public class StudentDto implements Serializable {
    private final Long id;
    private final String name;
    private final int age;
    private final String email;
}