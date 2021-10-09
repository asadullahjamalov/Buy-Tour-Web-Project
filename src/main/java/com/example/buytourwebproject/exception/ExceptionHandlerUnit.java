package com.example.buytourwebproject.exception;

import com.example.buytourwebproject.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerUnit extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AgentNotFoundException.class)
    public ExceptionResponse handleAgentNotFoundException(AgentNotFoundException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RequestNotFoundException.class)
    public ExceptionResponse handleRequestNotFoundException(RequestNotFoundException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OfferWasAlreadySentException.class)
    public ExceptionResponse handleOfferWasAlreadySentException(OfferWasAlreadySentException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestExpiredException.class)
    public ExceptionResponse handleOfferWasAlreadySentException(RequestExpiredException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ExceptionResponse handleOfferWasAlreadySentException(PasswordsDoNotMatchException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegistrationNotCompletedException.class)
    public ExceptionResponse handleRegistrationNotCompletedException(RegistrationNotCompletedException ex){
        return new ExceptionResponse(ex.errorMessage, ex.code);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleUnexpectedError(Exception ex){

        return new ExceptionResponse(ex.getMessage(),"Unexpected error");
    }

}
