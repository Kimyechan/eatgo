package com.example.eatgo.service;

public class PasswordWrongException extends RuntimeException{

    PasswordWrongException(){
        super("password is wrong");
    }
}
