package com.example.eatgo.service;

public class PasswordWrongException extends RuntimeException{

    public PasswordWrongException(){
        super("password is wrong");
    }
}
