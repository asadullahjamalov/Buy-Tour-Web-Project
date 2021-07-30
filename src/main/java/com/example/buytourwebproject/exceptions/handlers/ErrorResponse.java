package com.example.buytourwebproject.exceptions.handlers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
public class ErrorResponse {
    String message ;
    String code;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
