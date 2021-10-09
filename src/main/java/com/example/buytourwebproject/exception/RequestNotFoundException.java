package com.example.buytourwebproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException{
    String errorMessage;
    String code;

    public RequestNotFoundException(String errorMessage,String code) {
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.code=code;
    }
}
