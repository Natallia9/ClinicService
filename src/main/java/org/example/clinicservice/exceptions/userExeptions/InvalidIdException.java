package org.example.clinicservice.exceptions.userExeptions;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String message){
        super(message);
    }
}
