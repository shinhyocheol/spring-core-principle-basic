package com.core.example.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@AllArgsConstructor
@ToString
public class User {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "연락처를 입력해주세요.")
    private String phone;
    @Min(value = 1, message = "나이는 {value} 이상이어야 합니다.")
    private int age;
}
