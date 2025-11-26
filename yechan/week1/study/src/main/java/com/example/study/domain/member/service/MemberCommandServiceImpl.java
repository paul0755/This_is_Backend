package com.example.study.domain.member.service;

import com.example.study.domain.member.converter.MemberConverter;
import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.dto.MemberResponseDTO;
import com.example.study.domain.member.entity.Member;
import com.example.study.domain.member.enums.Gender;
import com.example.study.domain.member.repository.MemberRepository;
import com.example.study.global.apiPayload.Exception.handler.MemberHandler;
import com.example.study.global.apiPayload.code.status.ErrorStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    public Member RegisterMember(MemberRequestDTO.RegisterDTO request) {

        Member newMember = MemberConverter.toMember(request);

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberResponseDTO.PutMemberDTO UpdateMember(MemberRequestDTO.UpdateDTO request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Gender gender = null;

        switch(request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
            default:
                gender = Gender.NONE;
                break;
        }

        member.update(
                request.getName(),
                request.getAddress(),
                gender
        );

        memberRepository.save(member);

        return MemberConverter.toPutMemberResultDTO(member);
    }

    @Override
    @Transactional
    public void deleteMemberByEmail(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        memberRepository.delete(member);
    }

}
