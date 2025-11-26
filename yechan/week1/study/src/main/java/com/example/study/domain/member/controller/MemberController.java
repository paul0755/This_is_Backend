package com.example.study.domain.member.controller;

import com.example.study.domain.member.converter.MemberConverter;
import com.example.study.domain.member.dto.MemberRequestDTO;
import com.example.study.domain.member.dto.MemberResponseDTO;
import com.example.study.domain.member.entity.Member;
import com.example.study.domain.member.service.MemberCommandService;
import com.example.study.domain.member.service.MemberQueryService;
import com.example.study.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/register")
    public ApiResponse<MemberResponseDTO.RegisterResultDTO> Register(@RequestBody @Valid MemberRequestDTO.RegisterDTO request) {

        Member member = memberCommandService.RegisterMember(request);
        return ApiResponse.onSuccess(MemberConverter.toRegisterResultDTO(member));
    }

    @GetMapping("/")
    public ApiResponse<MemberResponseDTO.GetMemberDTO> getMember(@RequestParam String email) {

        Member member = memberQueryService.getMemberByEmail(email);

        return ApiResponse.onSuccess(MemberConverter.toGetMemberResultDTO(member));
    }

    @PutMapping("/")
    public ApiResponse<MemberResponseDTO.PutMemberDTO> UpdateMember(@RequestBody @Valid MemberRequestDTO.UpdateDTO request) {

        MemberResponseDTO.PutMemberDTO responseDTO= memberCommandService.UpdateMember(request);

        return ApiResponse.onSuccess(responseDTO);

    }

    @DeleteMapping("/")
    public ApiResponse<Void> deleteMember(@RequestParam String email) {

        memberCommandService.deleteMemberByEmail(email);

        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/email")
    public ApiResponse<String> CheckEmail(@RequestParam String email){

        memberQueryService.CheckEmail(email);

        return ApiResponse.onSuccess("사용 가능한 이메일입니다.");
    }


}
