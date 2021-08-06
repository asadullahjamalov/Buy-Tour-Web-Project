package com.example.buytourwebproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistrationNotCompletedException extends RuntimeException{
    String errorMessage;
    String code;

    public RegistrationNotCompletedException(String errorMessage,String code) {
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.code=code;
    }
}
