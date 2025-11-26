package com.example.study.domain.member.dto;

import com.example.study.domain.member.enums.Gender;
import com.example.study.domain.member.enums.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResultDTO{
        Long memberId;
        LocalDate createAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetMemberDTO{
        String name;
        String address;
        Integer point;
        Gender gender;
        MemberStatus status;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PutMemberDTO{
        String name;
        String address;
        Gender gender;
    }
}
