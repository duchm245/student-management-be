package com.example.studentmanagement.security.payload;

import lombok.Data;

@Data
public class LoginRequest {
//    @NotBlank
    private String username;

//    @NotBlank
    private String password;
}
