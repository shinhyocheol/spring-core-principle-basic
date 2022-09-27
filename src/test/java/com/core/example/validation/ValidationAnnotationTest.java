package com.core.example.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class ValidationAnnotationTest {

    @Test
    void createUserTest() {
        User user = new User("userA", null, 20);

        assertThat(user).isNotNull();
    }
}