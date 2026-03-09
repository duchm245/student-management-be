package com.example.studentmanagement.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
