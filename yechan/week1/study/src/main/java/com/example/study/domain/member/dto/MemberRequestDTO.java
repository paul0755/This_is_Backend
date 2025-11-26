package com.example.study.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class RegisterDTO{
        @NotBlank
        String name;
        @NotBlank
        String email;
        @NotBlank
        String password;
        @NotBlank
        String address;
        @NotNull
        Integer gender;

    }

    @Getter
    public static class UpdateDTO{
        @NotBlank
        String email;
        @NotBlank
        String name;
        @NotBlank
        String address;
        @NotNull
        Integer gender;
    }
}
