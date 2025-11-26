package com.backendstudy.week1.member.controller;

import com.backendstudy.week1.exception.ApiResponse;
import com.backendstudy.week1.member.dto.MemberReqDto;
import com.backendstudy.week1.member.dto.MemberResDto;
import com.backendstudy.week1.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 -> 201 Created
    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody MemberReqDto memberDto) {
        MemberResDto created = memberService.create(memberDto);

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(ApiResponse.success(HttpStatus.CREATED.value(), "회원 등록 성공", created));
    }

    // 전체 회원 조회 or 회원 email로 조회
    @GetMapping
    public ResponseEntity<?> getMember(@RequestParam(required = false) String email) {
        if(email != null) {
            MemberResDto member = memberService.get(email);
            // return ResponseEntity.ok(member);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(HttpStatus.OK.value(), "이메일로 회원 조회 성공", member));
        }
        else {
            List<MemberResDto> memberList = memberService.getAll();
            // return ResponseEntity.ok(memberList);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success(HttpStatus.OK.value(), "전체 회원 조회 성공", memberList));
        }
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberReqDto memberDto) {
        MemberResDto updated = memberService.update(id, memberDto);

        // return ResponseEntity.ok(updated);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK.value(), "회원 정보 수정 성공", updated));
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        // return ResponseEntity.noContent().build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK.value(), "회원 삭제 성공", null));
    }
}
