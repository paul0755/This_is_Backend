package com.backendstudy.week1.member.controller;

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

        System.out.println("name: " + created.getName());
        System.out.println("email: " + created.getEmail());

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(created);
    }

    // 전체 회원 조회 or 회원 email로 조회
    @GetMapping
    public ResponseEntity<?> getMember(@RequestParam(required = false) String email) {
        if(email != null) {
            MemberResDto member = memberService.get(email);
            return ResponseEntity.ok(member);
        }
        else {
            List<MemberResDto> memberList = memberService.getAll();
            return ResponseEntity.ok(memberList);
        }
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberReqDto memberDto) {
        MemberResDto updated = memberService.update(id, memberDto);

        return ResponseEntity.ok(updated);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
