package org.example.clinicservice.exceptions.userExceptions;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(String message){
        super(message);
    }
}
