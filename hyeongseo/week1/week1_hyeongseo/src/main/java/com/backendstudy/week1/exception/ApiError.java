package com.backendstudy.week1.exception;

import lombok.Builder;
import lombok.Getter;

// Validation 실패 시 사용할 Dto 객체
@Getter
@Builder
public class ApiError {
    private final String field; // 에러 필드명
    private final String rejectedValue; // 에러 필드 값
    private final String reason; // 에러 메세지 (이유)
}
