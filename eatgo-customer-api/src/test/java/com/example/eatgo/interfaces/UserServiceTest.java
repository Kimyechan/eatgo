package com.example.eatgo.interfaces;

import com.example.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }
    @Test
    public void registerUser(){
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }
}