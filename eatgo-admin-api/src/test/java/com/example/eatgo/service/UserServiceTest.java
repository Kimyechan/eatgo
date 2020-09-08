package com.example.eatgo.service;

import com.example.eatgo.domain.CategoryRepository;
import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

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
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();

        User mockUser = User.builder()
                .email("test@example.com")
                .name("tester")
                .level(1L)
                .build();

        mockUsers.add(mockUser);

        given(userRepository.findAll()).willReturn(mockUsers);
        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName(), is("tester"));
    }
}