package org.example.clinicservice.exceptions.userExeptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message){
        super(message);
    }
}
