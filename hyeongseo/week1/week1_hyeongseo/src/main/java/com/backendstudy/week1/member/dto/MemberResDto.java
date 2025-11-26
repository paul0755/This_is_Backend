package com.backendstudy.week1.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원 Response용 Dto (이름, 이메일)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberResDto {
    private String name;
    private Integer age;
    private String email;
}
