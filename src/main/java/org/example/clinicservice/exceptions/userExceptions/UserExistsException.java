package org.example.clinicservice.exceptions.userExceptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message){

        super(message);
    }
}
