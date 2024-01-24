package com.simplecrud.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactDbExceptionHandler {

    @ExceptionHandler(value = {ContactDbNotFoundException.class})
    public ResponseEntity<Object> handleContactDbNotFoundException
            (ContactDbNotFoundException contactDbNotFoundException)
    {
        ContactDbException cloudVendorException = new ContactDbException(
                contactDbNotFoundException.getMessage(),
                contactDbNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(cloudVendorException, HttpStatus.NOT_FOUND);
    }
}
