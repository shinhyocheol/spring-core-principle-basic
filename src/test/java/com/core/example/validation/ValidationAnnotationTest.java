package com.core.example.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ValidationAnnotationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @DisplayName("연락처는 빈값이므로 유효성 검사에 통과할 수 없다.")
    @Test
    void createUserButPhoneBlank() {
        User user = new User("userA", null, 20);
        Set<ConstraintViolation<User>> validate = validator.validate(user);

        validate.forEach(error -> {
            System.out.println("error.getMessage() = " + error.getMessage());
            assertThat(error.getMessage()).isEqualTo("연락처를 입력해주세요.");
        });
    }
}