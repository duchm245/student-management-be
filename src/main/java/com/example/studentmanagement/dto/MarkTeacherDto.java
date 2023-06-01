package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkTeacherDto {
    private String studentName;
    private List<Float> exam1; // kiem tra 15'
    private List<Float> exam2; // kiem tra 45'
    private List<Float> exam3; // kiem tra hoc ky
    private Double avg; // diem trung binh
}
