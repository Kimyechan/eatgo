package com.example.eatgo.service;

public class EmailExistedException extends RuntimeException{

    public EmailExistedException(String email){
        super("Email is already existed : " + email);
    }


}
