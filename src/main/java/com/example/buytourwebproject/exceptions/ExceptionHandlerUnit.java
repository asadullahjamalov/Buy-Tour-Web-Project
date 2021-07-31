package com.example.buytourwebproject.exceptions;

import com.example.buytourwebproject.exceptions.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerUnit extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgentNotFoundException.class)
    public ErrorResponse handleAgentNotFoundException(AgentNotFoundException ex){
        return new ErrorResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RequestNotFoundException.class)
    public ErrorResponse handleRequestNotFoundException(RequestNotFoundException ex){
        return new ErrorResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OfferWasAlreadySentException.class)
    public ErrorResponse handleOfferWasAlreadySentException(OfferWasAlreadySentException ex){
        return new ErrorResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestExpiredException.class)
    public ErrorResponse handleOfferWasAlreadySentException(RequestExpiredException ex){
        return new ErrorResponse(ex.errorMessage, ex.code);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)

    public ErrorResponse handleUnexpectedError(Exception ex){

        return new ErrorResponse(ex.getMessage(),"Unexpected error");
    }

}
