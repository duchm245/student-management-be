package com.example.studentmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private Map<String, String> details;
    private Instant timestamp;
    private String path;
}

