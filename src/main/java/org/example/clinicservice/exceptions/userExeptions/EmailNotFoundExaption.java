package org.example.clinicservice.exceptions.userExeptions;

public class EmailNotFoundExaption extends RuntimeException{

    public EmailNotFoundExaption(String message){
        super(message);
    }
}
