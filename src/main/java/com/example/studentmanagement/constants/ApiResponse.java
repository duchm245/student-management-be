package com.example.studentmanagement.constants;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private int status;
    private ApiDataItem[] data;

}
