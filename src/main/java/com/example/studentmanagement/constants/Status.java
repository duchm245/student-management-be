package com.example.studentmanagement.constants;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    KHONG_HOAT_DONG(0, "Không hoạt động"),
    HOAT_DONG(1, "Hoạt động");

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
