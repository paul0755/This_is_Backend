package com.example.study.domain.member.service;

import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.dto.MemberResponseDTO;
import com.example.study.domain.member.entity.Member;

public interface MemberCommandService {

    Member RegisterMember(MemberRequestDTO.RegisterDTO request);
    MemberResponseDTO.PutMemberDTO UpdateMember(MemberRequestDTO.UpdateDTO request);
    void deleteMemberByEmail(String email);
}
