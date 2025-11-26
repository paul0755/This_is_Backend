package com.example.study.domain.member.converter;

import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.dto.MemberResponseDTO;
import com.example.study.domain.member.enums.Gender;
import com.example.study.domain.member.entity.Member;

import java.time.LocalDate;

public class MemberConverter {

    // 회원가입 응답 DTO
    public static MemberResponseDTO.RegisterResultDTO toRegisterResultDTO(Member member){
        return MemberResponseDTO.RegisterResultDTO.builder()
                .memberId(member.getId())
                .createAt(LocalDate.now())
                .build();
    }

    //로그인 응답 DTO
    public static MemberResponseDTO.GetMemberDTO toGetMemberResultDTO(Member member){
        return MemberResponseDTO.GetMemberDTO.builder()
                .name(member.getName())
                .point(member.getPoint())
                .address(member.getAddress())
                .gender(member.getGender())
                .status(member.getStatus())
                .build();
  }

    // 회원수정 응답 DTO
    public static MemberResponseDTO.PutMemberDTO toPutMemberResultDTO(Member member){

        return MemberResponseDTO.PutMemberDTO.builder()
                .name(member.getName())
                .address(member.getAddress())
                .gender(member.getGender())
                .build();
    }

    // 회원가입 request -> 멤버 Entity 생성
    public static Member toMember(MemberRequestDTO.RegisterDTO request){

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

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .address(request.getAddress())
                .gender(gender)
                .build();

    }



}
