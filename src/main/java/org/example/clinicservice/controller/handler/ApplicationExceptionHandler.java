package org.example.clinicservice.controller.handler;

import org.example.clinicservice.exceptions.userExeptions.EmailNotFoundExсeption;
import org.example.clinicservice.exceptions.userExeptions.UserExistsException;
import org.example.clinicservice.exceptions.userExeptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorExtension> handleGeneralException(Exception ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                "An unexpected error occurred",
                "INTERNAL_SERVER_ERROR",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorExtension> handlerUserExistsException(UserExistsException ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "USER_EXISTS",
                "User already exists in the system"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlerUserNotFoundException(UserNotFoundException ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "USER_EXISTS",
                "This user is missing"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotFoundExсeption.class)
    public ResponseEntity<ErrorExtension> handlerEmailNotFoundExaption(EmailNotFoundExсeption ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "EMAIL_EXISTS",
                "This email does not exist"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

}
