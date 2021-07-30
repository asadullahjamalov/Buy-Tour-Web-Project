package com.example.buytourwebproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OfferWasAlreadySentException extends RuntimeException{
    String errorMessage;
    String code;

    public OfferWasAlreadySentException(String errorMessage,String code) {
        super(errorMessage);
        this.errorMessage=errorMessage;
        this.code=code;
    }
}
