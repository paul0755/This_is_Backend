package com.example.study.domain.member.service;

import com.example.study.domain.member.converter.MemberConverter;
import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.dto.MemberResponseDTO;
import com.example.study.domain.member.entity.Member;
import com.example.study.domain.member.repository.MemberRepository;
import com.example.study.global.apiPayload.Exception.handler.MemberHandler;
import com.example.study.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Override
    public void CheckEmail(String email){
        if(memberRepository.existsByEmail(email)){
            throw new MemberHandler(ErrorStatus.MEMBER_EMAIL_DUPLICATION);
        }
    }

    @Override
    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
