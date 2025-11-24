package com.backendstudy.week1.member.service;

import com.backendstudy.week1.member.domain.Member;
import com.backendstudy.week1.member.dto.MemberReqDto;
import com.backendstudy.week1.member.dto.MemberResDto;
import com.backendstudy.week1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public MemberResDto create(MemberReqDto memberDto) {
        Member member = Member.builder()
                    .name(memberDto.getName())
                    .email(memberDto.getEmail())
                    .password(memberDto.getPassword())
                    .build();

        Member saved = memberRepository.save(member);

        return MemberResDto.builder()
                .name(saved.getName())
                .email(saved.getEmail())
                .build();
    }

    // 전체 회원 조회
    public List<MemberResDto> getAll() {
        List<Member> memberList = memberRepository.findAll();

        List<MemberResDto> memberDtoList = new ArrayList<>();

        for(Member m : memberList) {
            MemberResDto memberResDto = MemberResDto.builder()
                    .name(m.getName())
                    .email(m.getEmail())
                    .build();

            memberDtoList.add(memberResDto);
        }

        return memberDtoList;
    }

    // 회원 1명 id로 조회
    public MemberResDto get(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return MemberResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    // 회원 1명 이메일로 조회
    public MemberResDto get(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return MemberResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    // 회원 정보 수정
    public MemberResDto update(Long id, MemberReqDto memberDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());

        return MemberResDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    // 회원 삭제
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
