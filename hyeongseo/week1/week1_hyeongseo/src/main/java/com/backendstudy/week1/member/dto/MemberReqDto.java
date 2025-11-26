package com.backendstudy.week1.member.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 Request용 Dto (이름, 이메일, 비밀 번호)
// 유효성 검사 추가

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberReqDto {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(max = 30, message = "이름은 30자 이하여야 합니다.")
    private String name;

    @NotNull(message = "나이는 필수 입력값입니다.")
    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    @Max(value = 120, message = "나이는 120 이하이어야 합니다.")
    private Integer age;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 50, message = "이메일은 50자 이하여야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 20, message = "패스워드는 8자 이상 20자 이하여야 합니다.")
    private String password;
}
