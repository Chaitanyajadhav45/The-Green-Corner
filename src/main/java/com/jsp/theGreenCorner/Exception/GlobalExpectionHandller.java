package com.jsp.theGreenCorner.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExpectionHandller {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataDuplicate(DataIntegrityViolationException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email is duplicat");
    }
}
