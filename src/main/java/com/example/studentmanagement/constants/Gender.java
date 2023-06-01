package com.example.studentmanagement.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    Men(0, "Nữ"),
    Woman(1, "Nam");

    private int value;
    private String description;

//    Status(int value, String description) {
//        this.value = value;
//        this.description = description;
//    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
