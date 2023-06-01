package com.example.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzDto {
    private String id;
    private String name;
    private String code;
    private String number;
    private String limitClass;
    private String type;
    private String status;
    private String address;
    private String startDate;
    private String endDate;
}
