package com.diazero.developerchallenge.handler;

import com.diazero.developerchallenge.model.error.ErrorMessage;
import com.diazero.developerchallenge.exception.GeneralException;
import com.diazero.developerchallenge.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException e){
        ErrorMessage error = new ErrorMessage("[ERROR]", HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        ErrorMessage error = new ErrorMessage("[NOT FOUND]", HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
