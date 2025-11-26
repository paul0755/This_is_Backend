package com.backendstudy.week1.member.service;

import com.backendstudy.week1.exception.DuplicateEmailException;
import com.backendstudy.week1.exception.MemberNotFoundException;
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

        if(memberRepository.findByEmail(memberDto.getEmail()).isPresent()) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                    .name(memberDto.getName())
                    .age(memberDto.getAge())
                    .email(memberDto.getEmail())
                    .password(memberDto.getPassword())
                    .build();

        Member saved = memberRepository.save(member);

        return MemberResDto.builder()
                .name(saved.getName())
                .age(memberDto.getAge())
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
                    .age(m.getAge())
                    .email(m.getEmail())
                    .build();

            memberDtoList.add(memberResDto);
        }

        return memberDtoList;
    }

    // 회원 1명 id로 조회
    public MemberResDto get(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("해당 회원이 존재하지 않습니다."));

        return MemberResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .build();
    }

    // 회원 1명 이메일로 조회
    public MemberResDto get(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MemberNotFoundException("해당 회원이 존재하지 않습니다."));

        return MemberResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .build();
    }

    // 회원 정보 수정
    public MemberResDto update(Long id, MemberReqDto memberDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("해당 회원이 존재하지 않습니다."));

        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());

        return MemberResDto.builder()
                .name(member.getName())
                .age(member.getAge())
                .email(member.getEmail())
                .build();
    }

    // 회원 삭제
    public void delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("해당 회원이 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
