package com.example.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void creation(){
        User user = User.builder()
                .email("test@example.com")
                .name("테스터")
                .level(100L)
                .build();

        assertThat(user.getName(), is("테스터"));
        assertThat(user.isAdmin(), is(true));
        assertThat(user.isActive(), is(true));

        user.deactive();

        assertThat(user.isActive(), is(false));
    }
}