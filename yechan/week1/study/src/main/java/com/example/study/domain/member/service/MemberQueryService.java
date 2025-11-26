package com.example.study.domain.member.service;

import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.entity.Member;

public interface MemberQueryService {

    void CheckEmail(String email);
    Member getMemberByEmail(String email);
}
