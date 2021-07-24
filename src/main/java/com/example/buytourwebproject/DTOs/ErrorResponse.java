package com.example.buytourwebproject.DTOs;

import lombok.Getter;

@Getter
public class ErrorResponse {
    String message ;
    String code;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
